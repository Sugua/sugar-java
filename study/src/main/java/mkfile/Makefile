# 目标文件: 依赖文件1 依赖文件2
# 第一条规则是默认执行规则:
x.txt: m.txt c.txt
	cat m.txt c.txt > x.txt

m.txt: a.txt b.txt
	cat a.txt b.txt > m.txt

# 当clean文件存在时 会被认为生成clean文件，需要加这个标记
.PHONY: clean
# clean 是一个伪目标:
clean:
	rm -f m.txt
	rm -f x.txt

#两个pwd一样
cd:
	pwd
	cd ..
	pwd

cd_ok:
	pwd;cd ..;pwd

cd_ok_ok:
	pwd;\
	cd ..;\
	pwd

cd_ok_ok_ok:
	pwd&&cd ..&&pwd

no_output:
	@echo 'not display'
	echo 'will display'

has_error:
	rm zzz.txt
	echo 'ok'

ignore_error:
	-rm zzz.txt
	echo 'ok啦'



