<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Modify</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Modify</div>
            <div class="panel-body">
                <form role="form" action="/board/modify" method="post">
                    <input type="hidden" id="pageNum" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
                    <input type="hidden" id="amount" name="amount" value="<c:out value="${cri.amount}"/>">
                    <input type="hidden" name='type' value="${cri.type}">
                    <input type="hidden" name='keyword' value="${cri.keyword}">
                    <div class="form-group">
                        <label>Bno</label> <input class="form-control" name="bno" readonly="readonly"
                                                  value="<c:out value="${board.bno}"/>">
                    </div>
                    <div class="form-group">
                        <label>Title</label> <input class="form-control" name="title"
                                                    value="<c:out value="${board.title}"/>">
                    </div>
                    <div class="form-group">
                        <label>Text area</label>
                        <textarea class="form-control" rows="3" name="content"><c:out
                                value="${board.content}"/></textarea>
                    </div>
                    <div class="form-group">
                        <label>Writer</label> <input class="form-control" name="writer" readonly="readonly"
                                                     value="<c:out value="${board.writer}"/>"/>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="regDate" readonly="readonly"
                               value='<fmt:formatDate pattern="yyyy/MM//dd" value="${board.regDate}"/>'>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="updateDate" readonly="readonly"
                               value='<fmt:formatDate pattern="yyyy/MM//dd" value="${board.updateDate}"/>'>
                    </div>
                    <button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
                    <button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
                    <button type="submit" data-oper='list' class="btn btn-info">List</button>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="../includes/footer.jsp" %>
    <script>
        $(document).ready(function () {
            var formObj = $("form");
            $("button").on("click", function (e) {
                e.preventDefault();

                var operation = $(this).data("oper");

                if (operation === 'remove') {
                    formObj.attr("action", "/board/remove");
                } else if (operation === 'list') {
                    formObj.attr("action","/board/list").attr("method","get");

                    var pageNumTag=$("input[name='pageNum']").clone();
                    var amountTag=$("input[name='amount']").clone();
                    var keywordTag=$("input[name='keyword']").clone();
                    var typeTag=$("input[name='type']").clone();

                    formObj.empty();

                    formObj.append(pageNumTag);
                    formObj.append(amountTag);
                    formObj.append(keywordTag);
                    formObj.append(typeTag);
                }
                formObj.submit();
            });
        });
    </script>
