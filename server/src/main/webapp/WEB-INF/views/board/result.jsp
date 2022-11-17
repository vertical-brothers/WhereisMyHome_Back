<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<div style="height: 70px"></div>
<%-- <c:if test="${empty userinfo}">
	<script type="text/javascript">
		alert("로그인 후 이용 가능한 페이지입니다.");
		location.href = "${root}/user?act=mvlogin";
	</script>
</c:if> --%>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글쓰기 결과</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-primary pt-3">등록 완료!!!</h2>
            <div class="card-body">
              <p class="card-text">글작성이 완료되었습니다.</p>
              <button type="button" id="btn-list" class="btn btn-outline-primary">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-danger pt-3">등록 실패T.T</h2>
            <div class="card-body">
              <p class="card-text">
                글작성에 문제가 있습니다. <br />
                잠시 후 시도해주세요.
              </p>
              <button type="button" id="btn-list" class="btn btn-outline-danger">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "";
      });
    </script>
<%@ include file="../common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
