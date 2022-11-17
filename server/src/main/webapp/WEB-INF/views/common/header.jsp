<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
  <link rel="stylesheet" href="${root}/assets/css/main.css" />
</head>
<body>
<!-- 상단 navbar start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
      <a class="navbar-brand text-primary fw-bold" id="btn-mv-index" href="${root}">
        <img src="${root}/assets/img/ssafy_logo.png" alt="" width="60" />
        Where Is My Home
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${root}/board/list?pgno=1&key=&word=">공지사항</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${root}/house/mvaptapi">아파트 매매정보</a>
          </li>
          
          <c:if test="${userinfo.userRole eq 'admin' }">    
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${root}/user/list">회원 관리</a>
          </li>
          </c:if>
        </ul>
        <!-- 로그인 전 -->
        <c:if test="${empty userinfo}">
        <ul class="navbar-nav me-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" id="btn-mv-join" aria-current="page" href="${root}/user/join">회원가입</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btn-mv-login" aria-current="page" href="${root}/user/login">로그인</a>
          </li>
        </ul>
        </c:if>
        <!-- 로그인 후 -->
        <c:if test="${!empty userinfo}">

        <ul class="navbar-nav me-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link"><strong>${userinfo.userName}</strong> (${userinfo.userId})님</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${root}/user/logout">로그아웃</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="btn-info" aria-current="page" href="${root}/user/info">회원정보</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${root}/star/liststar">관심지역</a>
          </li>
        </ul>
        </c:if>
      </div>
    </div>
  </nav>
  <script>
	  
    </script>
</body>
</html>

