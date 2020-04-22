# notes
用于学习笔记



<u>以下用于 常见的使用情况</u>

#### 在Windows系统里创建.gitignore文件

1. 在项目根目录下面创建gitignore.txt文件
2. 把你需要排除的文件名保存到gitignore.txt文件
3. 在项目根目录下面按住Shift键并邮件然后选择“在此处打开命令窗口”
4. 执行命令 `ren gitignore.txt .gitignore ` 大功告成了！

写入目录树到文件：

`tree /F >dirPath.txt`

增加执行权限 `chmod a+x startRedis.sh`   

列出谁在使用某个端口 如： `lsof -i :3306`

vim中 `:set nu` 查看行号

vim中跳到最后一行 `shift+g` 即G

vim中跳到第一行 按两次 小`g`



##### CentOS7关闭防火墙和selinux

直接上命令
在root用户下
`systemctl stop firewalld`
`systemctl disable firewalld`
`systemctl status firewalld`
`vi /etc/selinux/config`
把`SELINUX=enforcing` 改成 `SELINUX=disabled`
重启电脑就可以了



##### linux 更改 文件中的内容

`sed -i “s/要替换的内容/替换后的内容/g” 文件名`

使用#代替/能够适应替换内容中含有/的内容，不需要转译.不然还要使用//转译！.*是匹配所有内容！

如  `sed -i 's#bind 0.0.0.0#bind 127.0.0.1#g' ./aaa.conf`




