<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Where Is My Home</title>

</head>

<body>

<div class="container">
    <div style="height: 70px"></div>
    <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">회원 관리</mark>
          </h2>
        </div>
        </div>
       <table class="table table-hover">
            <thead>
              <tr class="text-center">
              <th scope="col">번호</th>
                <th scope="col">아이디</th>
                <th scope="col">이름</th>
                <th scope="col">이메일</th>
                <th scope="col">전화번호</th>
                <th scope="col">역할</th>
                <th scope="col">탈퇴</th>
              </tr>
            </thead>
            <tbody>
			<c:forEach var="user" items="${users}" varStatus="status">
              <tr class="text-center">
                <th scope="row">${status.count}</th>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.userEmail}</td>
                <td>${user.userPhone}</td>
                <td>${user.userRole}</td>
                <c:if test="${user.userDel eq 1}">
                <td>O</td>
                </c:if>
                <c:if test="${user.userDel eq 0}">
                <td>X</td>
                </c:if>
              </tr>
			</c:forEach>
            </tbody>
          </table>

  </div>

 



 <%@include file="../common/footer.jsp" %>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    
    
</body>

</html>