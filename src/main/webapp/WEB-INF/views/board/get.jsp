<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Read</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Read</div>
            <div class="panel-body">
                <div class="form-group">
                    <label>Bno</label> <input class="form-control" name="bno" readonly="readonly" value="<c:out value="${board.bno}"/>">
                </div>
                <div class="form-group">
                    <label>Title</label> <input class="form-control" name="title" value="<c:out value="${board.title}"/>" readonly="readonly">
                </div>
                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
                </div>
                <div class="form-group">
                    <label>Writer</label> <input class="form-control" name="writer"  readonly="readonly" value="<c:out value="${board.writer}"/>" />
                </div>
                <button data-oper='modify' class="btn btn-default">Modify</button>
                <button data-oper='list' class="btn btn-info">List</button>
                <form id='operForm' action="/board/modify" method="get">
                    <input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}"/>">
                    <input type="hidden" id="pageNum" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
                    <input type="hidden" id="amount" name="amount" value="<c:out value="${cri.amount}"/>">
                    <input type="hidden" name='type' value="${cri.type}">
                    <input type="hidden" name='keyword' value="${cri.keyword}">
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
    console.log("==========JS TEST==========")
    var bnoValue='<c:out value="${board.bno}"/>';

   /* replyService.add({
        reply:"JS ADD REPLY TEST",replyer:"DONG",bno:bnoValue
    },
    function (result) {
        alert("RESULT: "+result);
    })*/
   /* replyService.getList({bno:bnoValue,page:1},function (list) {
        for(var i=0,len=list.length||0;i<len;i++){
            console.log(list[i]);
        }
    })*/
    /*replyService.remove(21,function (result){
        if(result==="success"){
            alert("REMOVED");
        }
    },
    function (err) {
        alert("ERROR...")
    });*/
  /*  replyService.update({
        rno:1,bno:bnoValue,reply:"수정되었음."
    },function (result) {
        alert(result);
    })*/
    replyService.get(1,function (data) {
        console.log(data);
    })


</script>


<script>
    $(document).ready(function () {
        var operForm=$("#operForm");

        $("button[data-oper='modify']").on("click",function (e){
            operForm.attr("action","/board/modify").submit();
        });
        $("button[data-oper='list']").on("click",function (e) {
            operForm.find("#bno").remove();
            operForm.attr("action","/board/list").submit();
        });
    });
</script>
