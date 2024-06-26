package com.sugar;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/5/20 9:29 AM
 * @Version 1.0
 */
public class WorldReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    //第一个参数 Text是reduce的输入的 key（数据类型与map的输出key一致）
    //第二个参数 LongWritable 是reduce的输入的 value（数据类型与map的输出value一致）
    //第三个参数 Text是reduce的输出的 key（数据类型reduce的输入的 key一致）
    //第四个参数 LongWritable 是reduce的输出的 value（数据类型reduce的输入的 value一致）

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        //key 表示去重后的单词
        //value 表示标记的1 （好多 1，key出现几次就有几个1）
        int count = 0;
        for (IntWritable iw : values) {

            count += iw.get();
        }
        context.write(key, new IntWritable(count));
    }
}
