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
    <main class="form-signin w-100 m-auto" id="user-info">
      <form id="form-update" method="POST" action=""> 
      <input type="hidden" name="act" value="update">
          <h1 class="h3 mb-3 fw-normal">회원 정보 확인</h1>
  
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="userid" name="userId" value="${user.userId}" readonly>
            <label for="userid">ID</label>
          </div>
          <div class="form-floating mb-1">
            <input type="password" class="form-control" id="userpwd" name="userPwd" value="">
            <label for="userpwd">Password</label>
          </div>
          <div class="form-floating mb-1">
            <input type="email" class="form-control" id="useremail" name="userEmail" value="${user.userEmail}">
            <label for="useremail">email</label>
          </div>
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="username" name="userName" value="${user.userName}">
            <label for="username">name</label>
          </div>
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="userphone" name="userPhone" value="${user.userPhone}">
            <label for="userphone">phone</label>
          </div>
          <div class="form-floating mb-3">
            <input type="text" class="form-control" id="userrole" name="userRole" value="${user.userRole}" readonly>
            <label for="userrole">role</label>
          </div>
          <button class="w-100 btn btn-lg btn-primary mb-1" id="btn-update" type="button">정보 수정</button>
          <button class="w-100 btn btn-lg btn-primary" id="btn-delete" type="button">회원 탈퇴</button>
        </form>
      
      
    </main>
  </div>


 <%@include file="../common/footer.jsp" %>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    
    <script>
	  // 회원탈퇴   
      document.querySelector("#btn-delete").addEventListener("click", function () {
    	location.href = "${root}/user/delete";
      });
   // 회원정보수정  
      document.querySelector("#btn-update").addEventListener("click", function () {
    	  if (!document.querySelector("#username").value) {
              alert("이름 입력!!");
              return;
            } else if (!document.querySelector("#userpwd").value) {
              alert("비밀번호 입력!!");
              return;
            } else {
              let form = document.querySelector("#form-update");
              form.setAttribute("action", "${root}/user/update");
              form.submit();
            }
      });
   
   
	  
	 </script>
</body>

</html>