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
    <main class="form-signin w-100 m-auto ">
      <form id="form-login" method="POST" action="">
      <input type="hidden" name="act" value="login">
        <h1 class="h3 mb-3 fw-normal " style="text-align: center;">Log In</h1>

        <div class="form-floating mb-1">
          <input type="text" class="form-control" name="userId" id="userid" placeholder="ID">
          <label for="userid">ID</label>
        </div>
        <div class="form-floating mb-1">
          <input type="password" class="form-control" name="userPwd" id="userpwd" placeholder="Password">
          <label for="userpwd">Password</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary mb-1" id="btn-login" type="button">Login</button>
      </form>
    </main>
  </div>


  <%@include file="../common/footer.jsp" %>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
  <script>
	  // 로그인
      document.querySelector("#btn-login").addEventListener("click", function () {
        //alert("로그인 버튼 클릭 완료");
    	  if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-login");
          form.setAttribute("action", "${root}/user/login");
          form.submit();
        }
      });
	 
    </script>
</body>

</html>