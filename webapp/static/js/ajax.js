/**
 * Created by xsank.mz on 2016/5/29.
 */

var request;

function question(){
    var element=document.getElementById("question");
    var input=element.value;
    element.value="";
    record("human",input);
    var url="shour?question="+input
    if(window.XMLHttpRequest){
        request=new XMLHttpRequest();
    }else if(window.ActiveXObject){
        request=new ActiveXObject("Microsoft.XMLHTTP");
    }
    request.open("GET",url,true);
    request.onreadystatechange=callback;
    request.send(null);
}

function callback(){
    if(request.readyState==4 && request.status==200){
        var response=request.responseText;
        answer(response);
    }
}

function answer(response){
    record("robot",response);
}

function record(user,text){
    var line=user+"->"+text+"\n";
    var history=document.getElementById("history");
    history.value+=line;
}