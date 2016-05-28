小二(Shour)
====================

小二是一个简单的问答机器人，其中根据自己的业务需要，对于文档内容进行定制。   
此外，对于稍微复杂的功能，比如询问时间，查询天气等，小二也做了支持，如果业务有类似的接口需求，实现自己特有的Ability即可。  
小二还增加了一些预处理的功能，比如统计，过滤等，如果业务有类似的预处理功能，可以通过实现Handler接口来实现。  

License: `MIT` (see LICENSE)


结构图
-------
<div align="center">
    <img src="https://raw.githubusercontent.com/xsank/Shour/master/doc/structure.jpg" alt="结构图" />
</div>


使用方式
-----------

> 1.配置自己的定制化Knowledge.json问答 (必选)  
> 2.看是否有定制化功能需求，添加自己的Ability  
> 3.看是否有定制化预处理功能，添加自己的Handler  
> 4.在Actor.properties中进行配置 (必选)  


样例
-------
> question->你好  
> answer->你好，小二将竭诚为您介绍服务，解答疑问  

> question->你是谁  
> answer->在下小二，请多指教  

> question->我喜欢你  
> answer->不要这么说我会害羞的  

> question->我不开心  
> answer->为什么  

> question->你猜  
> answer->人家不要猜，你快揭晓答案吧  

> question->你们几点上班？  
> answer->小二全年365天午休，竭诚为您服务  

> question->杭州天气怎么样  
> answer->杭州:天气:东风,温度20:,湿度:36%  

> question->你真笨  
> answer->对不起,我会改进的  

> question->哈哈   
> answer->很抱歉，不知道您的意思  

> question->哈哈  
> answer->很抱歉，不知道您的意思  

> question->哈哈  
> answer->有意思没  


不足
------
1.使用coreNLP来做中文处理，启动加载时间太长，要1分多钟，同时需要消耗2g的内存空间；同时coreNLP缺少对于情感分析，同义词分析的支持  
2.没有添加方便的功能演示页面