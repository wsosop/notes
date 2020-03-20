# github 小技巧

## 1）、搜索开源项目：

### 1.1)、使用基本

```
in:name example	名字中有“example”
in:readme example	readme中有“example”
in:description example	描述中有“example”

stars:>1000	star>1000
forks:>1000	fork>1000
pushed:>2019-09-01	2019年9月1日后有更新的

language:java	用Java编写的项目  
```

### [github帮助文档（中文）](https://help.github.com/cn/github/searching-for-information-on-github/searching-for-repositories)

链接：https://help.github.com/cn/github/searching-for-information-on-github/searching-for-repositories



# 2）、官方文档：

您可以在 GitHub 上搜索仓库，并使用这些仓库搜索限定符的任意组合缩小结果范围。

- 对多个字词的搜索词使用引号。 例如，如果要搜索具有标签 "In progress" 的议题，可搜索 `label:"in progress"`。 搜索不区分大小写。

### [按仓库名称、说明或自述文件内容搜索]

通过 `in` 限定符，您可以将搜索限制为仓库名称、仓库说明、自述文件内容或这些的任意组合。 如果省略此限定符，则只搜索仓库名称和说明。

| 限定符            | 示例                                                         |
| ----------------- | ------------------------------------------------------------ |
| `in:name`         | [**jquery in:name**](https://github.com/search?q=jquery+in%3Aname&type=Repositories) 匹配其名称中含有 "jquery" 的仓库。 |
| `in:description`  | [**jquery in:name,description**](https://github.com/search?q=jquery+in%3Aname%2Cdescription&type=Repositories) 匹配其名称或说明中含有 "jquery" 的仓库。 |
| `in:readme`       | [**jquery in:readme**](https://github.com/search?q=jquery+in%3Areadme&type=Repositories) 匹配其自述文件中提及 "jquery" 的仓库。 |
| `repo:owner/name` | [**repo:octocat/hello-world**](https://github.com/search?q=repo%3Aoctocat%2Fhello-world) 匹配特定仓库名称。 |

### [基于仓库的内容搜索]

您可以使用 `in:readme` 限定符，通过搜索其自述文件中的内容来查找仓库。

除了使用 `in:readme` 以外，无法通过搜索仓库内的特定内容来查找仓库。 要搜索仓库内的特定文件或内容，您可以使用查找器或代码特定的搜索限定符。 更多信息请参阅“[在 GitHub 上查找文件](https://help.github.com/cn/articles/finding-files-on-github)”和“[搜索代码](https://help.github.com/cn/articles/searching-code)”。

| 限定符      | 示例                                                         |
| ----------- | ------------------------------------------------------------ |
| `in:readme` | [**octocat in:readme**](https://github.com/search?q=octocat+in%3Areadme&type=Repositories) 匹配其自述文件中提及 "octocat" 的仓库。 |

### [在用户或组织的仓库内搜索]

要在特定用户或组织拥有的所有仓库中搜索，您可以使用 `user` 或 `org` 限定符。

| 限定符            | 示例                                                         |
| ----------------- | ------------------------------------------------------------ |
| `user:*USERNAME*` | [**user:defunkt forks:>100**](https://github.com/search?q=user%3Adefunkt+forks%3A>%3D100&type=Repositories) 匹配来自 @defunkt、拥有超过 100 复刻的仓库。 |
| `org:*ORGNAME*`   | [**org:github**](https://github.com/search?utf8=✓&q=org%3Agithub&type=Repositories) 匹配来自 GitHub 的仓库。 |

### [按仓库大小搜索]

`size` 限定符使用[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)查找匹配特定大小（以千字节为单位）的仓库。

| 限定符     | 示例                                                         |
| ---------- | ------------------------------------------------------------ |
| `size:*n*` | [**size:1000**](https://github.com/search?q=size%3A1000&type=Repositories) 匹配恰好为 1 MB 的仓库。 |
|            | [**size:>=30000**](https://github.com/search?q=size%3A>%3D30000&type=Repositories) 匹配至少为 30 MB 的仓库。 |
|            | [**size:<50**](https://github.com/search?q=size%3A<50&type=Repositories) 匹配小于 50 KB 的仓库。 |
|            | [**size:50..120**](https://github.com/search?q=size%3A50..120&type=Repositories) 匹配介于 50 KB 与 120 KB 之间的仓库。 |

### [按关注者数量搜索]

您可以使用 `followers` 限定符以及[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)基于仓库拥有的关注者数量过滤仓库。

| 限定符          | 示例                                                         |
| --------------- | ------------------------------------------------------------ |
| `followers:*n*` | [**node followers:>=10000**](https://github.com/search?q=node+followers%3A>%3D10000) matches repositories with 10,000 or more followers mentioning the word "node". |
|                 | [**styleguide linter followers:1..10**](https://github.com/search?q=styleguide+linter+followers%3A1..10&type=Repositories) 匹配拥有 1 到 10 个关注者并且提及 "styleguide linter" 一词的的仓库。 |

### [按复刻数量搜索]

`forks` 限定符使用[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)指定仓库应具有的复刻数量。

| 限定符      | 示例                                                         |
| ----------- | ------------------------------------------------------------ |
| `forks:*n*` | [**forks:5**](https://github.com/search?q=forks%3A5&type=Repositories) 匹配只有 5 个复刻的仓库。 |
|             | [**forks:>=205**](https://github.com/search?q=forks%3A>%3D205&type=Repositories) 匹配具有至少 205 个复刻的仓库。 |
|             | [**forks:<90**](https://github.com/search?q=forks%3A<90&type=Repositories) 匹配具有少于 90 个复刻的仓库。 |
|             | [**forks:10..20**](https://github.com/search?q=forks%3A10..20&type=Repositories) 匹配具有 10 到 20 个复刻的仓库。 |

### [按星号数量搜索]

您可以使用[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)基于仓库具有的[星标](https://help.github.com/cn/articles/saving-repositories-with-stars)数量搜索仓库

| 限定符      | 示例                                                         |
| ----------- | ------------------------------------------------------------ |
| `stars:*n*` | [**stars:500**](https://github.com/search?utf8=✓&q=stars%3A500&type=Repositories) 匹配恰好具有 500 个星号的仓库。 |
|             | [**stars:10..20**](https://github.com/search?q=stars%3A10..20+size%3A<1000&type=Repositories) 匹配具有 10 到 20 个星号、小于 1000 KB 的仓库。 |
|             | [**stars:>=500 fork:true language:php**](https://github.com/search?q=stars%3A>%3D500+fork%3Atrue+language%3Aphp&type=Repositories) 匹配具有至少 500 个星号，包括复刻的星号（以 PHP 编写）的仓库。 |

### [按仓库创建或上次更新时间搜索]

您可以基于创建时间或上次更新时间过滤仓库。 对于仓库创建，您可以使用 `created` 限定符；要了解仓库上次更新的时间，您要使用 `pushed` 限定符。 `pushed` 限定符将返回仓库列表，按仓库中任意分支上最近进行的提交排序。

两者均采用日期作为参数。 日期格式必须遵循 [ISO8601](http://en.wikipedia.org/wiki/ISO_8601)标准，即 `YYYY-MM-DD`（年-月-日）。 您也可以在日期后添加可选的时间信息 `THH:MM:SS+00:00`，以便按小时、分钟和秒进行搜索。 这是 `T`，随后是 `HH:MM:SS`（时-分-秒）和 UTC 偏移 (`+00:00`)。

日期支持[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)。

| 限定符                 | 示例                                                         |
| ---------------------- | ------------------------------------------------------------ |
| `created:*YYYY-MM-DD*` | [**webos created:<2011-01-01**](https://github.com/search?q=webos+created%3A<2011-01-01&type=Repositories) 匹配具有 "webos" 字样、在 2011 年之前创建的仓库。 |
| `pushed:*YYYY-MM-DD*`  | [**css pushed:>2013-02-01**](https://github.com/search?utf8=✓&q=css+pushed%3A>2013-02-01&type=Repositories) 匹配具有 "css" 字样、在 2013 年 1 月之后收到推送的仓库。 |
|                        | [**case pushed:>=2013-03-06 fork:only**](https://github.com/search?q=case+pushed%3A>%3D2013-03-06+fork%3Aonly&type=Repositories) 匹配具有 "case" 字样、在 2013 年 3 月 6 日或之后收到推送并且作为复刻的仓库。 |

### [按语言搜索]

您可以基于其编写采用的主要语言搜索仓库。

| 限定符                | 示例                                                         |
| --------------------- | ------------------------------------------------------------ |
| `language:*LANGUAGE*` | [**rails language:javascript**](https://github.com/search?q=rails+language%3Ajavascript&type=Repositories) 匹配具有 "rails" 字样、以 JavaScript 编写的仓库。 |

### [按主题搜索]

您可以查找归类为特定[主题](https://help.github.com/cn/articles/classifying-your-repository-with-topics)的所有仓库。

| 限定符          | 示例                                                         |
| --------------- | ------------------------------------------------------------ |
| `topic:*TOPIC*` | [**topic:jekyll**](https://github.com/search?utf8=✓&q=topic%3Ajekyll&type=Repositories&ref=searchresults)匹配已归类为 "jekyll" 主题的仓库。 |

### [按主题数量搜索]

您可以使用 `topics` 限定符以及[大于、小于和范围限定符](https://help.github.com/cn/articles/understanding-the-search-syntax)按应用于仓库的[主题](https://help.github.com/cn/articles/classifying-your-repository-with-topics)数量搜索仓库。

| 限定符       | 示例                                                         |
| ------------ | ------------------------------------------------------------ |
| `topics:*n*` | [**topics:5**](https://github.com/search?utf8=✓&q=topics%3A5&type=Repositories&ref=searchresults) 匹配具有五个主题的仓库。 |
|              | [**topics:>3**](https://github.com/search?utf8=✓&q=topics%3A>3&type=Repositories&ref=searchresults) matches repositories that have more than three topics. |

### [按许可搜索]

您可以按其[许可](https://help.github.com/cn/articles/licensing-a-repository)搜索仓库。 您必须使用[许可关键词](https://help.github.com/cn/articles/licensing-a-repository/#searching-github-by-license-type)按特定许可或许可系列过滤仓库。

| 限定符                      | 示例                                                         |
| --------------------------- | ------------------------------------------------------------ |
| `license:*LICENSE_KEYWORD*` | [**license:apache-2.0**](https://github.com/search?utf8=✓&q=license%3Aapache-2.0&type=Repositories&ref=searchresults) 匹配根据 Apache License 2.0 授权的仓库。 |

### [按公共或私有仓库搜索]

您可以基于仓库是公共还是私有来过滤搜索。

| 限定符       | 示例                                                         |
| ------------ | ------------------------------------------------------------ |
| `is:public`  | [**is:public org:github**](https://github.com/search?q=is%3Apublic+org%3Agithub&type=Repositories&utf8=✓) 匹配 GitHub 拥有的公共仓库。 |
| `is:private` | [**is:private pages**](https://github.com/search?utf8=✓&q=pages+is%3Aprivate&type=Repositories) 匹配您有访问权限且包含 "pages" 字样的私有仓库。 |

### [基于仓库是否为镜像搜索]

You can search repositories based on whether or not they're a mirror and are hosted elsewhere. For more information, see "[Finding ways to contribute to open source on GitHub](https://help.github.com/cn/github/getting-started-with-github/finding-ways-to-contribute-to-open-source-on-github)."

| 限定符         | 示例                                                         |
| -------------- | ------------------------------------------------------------ |
| `mirror:true`  | [**mirror:true GNOME**](https://github.com/search?utf8=✓&q=mirror%3Atrue+GNOME&type=) 匹配是镜像且包含 "GNOME" 字样的仓库。 |
| `mirror:false` | [**mirror:false GNOME**](https://github.com/search?utf8=✓&q=mirror%3Afalse+GNOME&type=) 匹配并非镜像且包含 "GNOME" 字样的仓库。 |

### [基于仓库是否已存档搜索]

您可以基于仓库是否[已存档](https://help.github.com/cn/articles/about-archiving-repositories)来搜索仓库。

| 限定符           | 示例                                                         |
| ---------------- | ------------------------------------------------------------ |
| `archived:true`  | [**archived:true GNOME**](https://github.com/search?utf8=✓&q=archived%3Atrue+GNOME&type=) 匹配已存档且包含 "GNOME" 字样的仓库。 |
| `archived:false` | [**archived:false GNOME**](https://github.com/search?utf8=✓&q=archived%3Afalse+GNOME&type=) 匹配未存档且包含 "GNOME" 字样的仓库。 |

### [基于具有 `good first issue` 或 `help wanted` 标签的议题数量搜索]

您可以使用限定符 `help-wanted-issues:>n` 和 `good-first-issues:>n` 搜索具有最少数量标签为 `help-wanted` 或 `good-first-issue` 议题的仓库。 For more information, see "[Encouraging helpful contributions to your project with labels](https://help.github.com/cn/github/building-a-strong-community/encouraging-helpful-contributions-to-your-project-with-labels)."

| 限定符                  | 示例                                                         |
| ----------------------- | ------------------------------------------------------------ |
| `good-first-issues:>n`  | [**good-first-issues:>2 javascript**](https://github.com/search?utf8=✓&q=javascript+good-first-issues%3A>2&type=) 匹配具有超过两个标签为 `good-first-issue` 的议题且包含 "javascript" 字样的仓库。 |
| `help-wanted-issues:>n` | [**help-wanted-issues:>4 react**](https://github.com/search?utf8=✓&q=react+help-wanted-issues%3A>4&type=) 匹配具有超过四个标签为 `help-wanted` 的议题且包含 "React" 字样的仓库。 |

### [延伸阅读](https://help.github.com/cn/github/searching-for-information-on-github/searching-for-repositories#further-reading)

- “[排序搜索结果](https://help.github.com/cn/articles/sorting-search-results)”
- “[在复刻中搜索](https://help.github.com/cn/articles/searching-in-forks)”