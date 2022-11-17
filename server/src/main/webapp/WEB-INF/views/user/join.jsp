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

      <main class="form-signin w-100 m-auto">
        <form id="form-join" method="POST" action="">
        <input type="hidden" name="act" value="join">
          <h1 class="h3 mb-3 fw-normal" style="text-align: center;">Sign Up</h1>
  
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="userid" name="userId" placeholder="ID">
            <label for="userid">ID</label>
          </div>
          <div id="idcheck-result"></div>
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="username" name="userName" placeholder="name">
            <label for="username">name</label>
          </div>
          <div class="form-floating mb-1">
            <input type="password" class="form-control" id="userpwd" name="userPwd" placeholder="Password">
            <label for="userpwd">Password</label>
          </div>
          <div class="form-floating mb-1">
            <input type="email" class="form-control" id="useremail" name="userEmail" placeholder="email">
            <label for="useremail">email</label>
          </div>
          <div class="form-floating mb-1">
            <input type="text" class="form-control" id="userphone" name="userPhone" placeholder="phone">
            <label for="userphone">phone</label>
          </div>
          <div class="form-floating mb-3">
            <input type="text" class="form-control" id="userrole" name="userRole" placeholder="role">
            <label for="userrole">role</label>
          </div>
          <button class="w-100 btn btn-lg btn-primary" id="btn-join" type="button">Sign Up</button>
        </form>
      </main>
    </div>


<%@include file="../common/footer.jsp" %>
  </body>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
 	<script>
      let isUseId = false;
      document.querySelector("#userid").addEventListener("keyup", function () {
    	 let userid = this.value;
    	 let resultDiv = document.querySelector("#idcheck-result");
    	 if(userid.length < 5 || userid.length > 16) {
    		 resultDiv.setAttribute("class", "mb-3 text-dark");
    		 resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
    		 isUseId = false;
    	 } else {
			fetch("${root}/user/idcheck/"+userid)
				.then(response => response.text())
				.then(data => {
					console.log(data);
	    			 if(data == 0) {
	    			   resultDiv.setAttribute("class", "mb-3 text-primary");
	    		       resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	    		       isUseId = true;
	    			 } else {
	    			   resultDiv.setAttribute("class", "mb-3 text-danger");
	      		       resultDiv.textContent = userid + "는 사용할 수 없습니다.";
	      		       isUseId = false;
	    			 }
				});
    	 }
      });
      
      document.querySelector("#btn-join").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (!isUseId) {
          alert("아이디 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/user/join");
          form.submit();
        }
      });
    </script>
</html>
