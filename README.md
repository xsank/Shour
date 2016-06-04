小二(Shour)
====================

小二是一个简单的问答机器人，其中根据自己的业务需要，对于问答内容进行定制即可  
如需要稍微复杂的功能，比如线路查询，百科，笑话等，实现小二的Ability即可，参考小二的天气查询  
另外还可能存在一些通用的预处理功能，比如敏感词屏蔽，可以通过实现Handler接口来实现，参考小二的统计功能    

License: `MIT` (see LICENSE)


web演示
--------
<div align="center">
    <img src="https://raw.githubusercontent.com/xsank/Shour/master/doc/demo.jpg" alt="演示图" />
</div>


结构图
-------
<div align="center">
    <img src="https://raw.githubusercontent.com/xsank/Shour/master/doc/structure.jpg" alt="结构图" />
</div>


使用方式
-----------

基础功能：  
> 1.配置自己的定制化Knowledge.json问答 (必选)  
> 2.在Actor.properties中配置功能 (必选)  

扩展扩展：  
> 1.通过添加handler来完成一些预处理工作，比如敏感词过滤  
> 2.通过添加自己定义的ability来完成更多定制功能，比如交通线查询，百科等  


对话样例
--------
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
1. 使用coreNLP来做中文处理，启动加载时间太长，要1分多钟，同时需要消耗2g的内存空间；  
2. coreNLP缺少对于情感分析，同义词分析的支持，使得小二在业务问答环节可以基本满足，但对于通俗聊天支持不足；  
3. 暂且没有上下文记忆功能  
