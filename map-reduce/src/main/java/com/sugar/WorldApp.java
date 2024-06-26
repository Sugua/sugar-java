package com.sugar;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/5/20 9:35 AM
 * @Version 1.0
 */
public class WorldApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

//        System.out.println(args.length);
//        if (args.length > 0) {
//            for (String s : args) {
//
//                System.out.println(s);
//            }
//        }
        //conf.set("fs.defaultFS","file:///");

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        if (args.length > 1) {
            FileSystem.get(conf).delete(new Path(args[1]),true);
        }

        job.setJobName("WorldApp");
        job.setJarByClass(WorldApp.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WorldMapper.class);
        job.setReducerClass(WorldReducer.class);

        job.setNumReduceTasks(1);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);


    }
}
