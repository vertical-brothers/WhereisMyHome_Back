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
            <mark class="sky">글목록</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
            <%-- <c:if test="${userinfo.userRole eq 'admin' }"> --%>
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                글쓰기
              </button>
              <%-- </c:if> --%>
            </div>
            <div class="col-md-7 offset-3">
              <form class="d-flex" id="form-search" action="">
                <input type="hidden" name="act" value="list"/>
                <input type="hidden" name="pgno" value="1"/>
                <select
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  name="key"
                  aria-label="검색조건"
                >
                  <option value="" selected>검색조건</option>
                  <option value="subject">제목</option>
                  <option value="userid">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" name="word" placeholder="검색어..." />
                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
          <c:if test="${!empty articles}">
			<c:forEach var="article" items="${articles}">
              <tr class="text-center">
                <th scope="row">${article.articleNo}</th>
                <td class="text-start">
                  <a
                    href="#"
                    class="article-title link-dark"
                    data-no="${article.articleNo}"
                    style="text-decoration: none"
                  >
                    ${article.subject}
                  </a>
                </td>
                <td>${article.userId}</td>
                <td>${article.hit}</td>
                <td>${article.registerTime}</td>
              </tr>
             </c:forEach>
           </c:if>
            </tbody>
          </table>
        </div>
        <div class="row">
			<ul class="pagination justify-content-center">
				<c:if test="${param.pgno - 1 > 0 }">
					<li class="page-item"><a class="page-link" href="${root}/board/list?pgno=${param.pgno-1}&key=&word=">이전</a></li>
				</c:if>
				<c:if test="${param.pgno - 1 <= 0}">
					<li class="page-item"><a class="page-link" href="#" onClick="alert('이전 페이지가 없습니다.')" >이전</a></li>
				</c:if>
				
				<c:forEach var="page" begin="${startpageno }" end="${endpage}" varStatus="page_num">
					<c:choose>
						<c:when test="${page_num.count eq param.pgno}"> 
							<li class="page-item active"><a class="page-link" href="${root}/board/list?pgno=${page }&key=&word=&articleno=#">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="${root}/board/list?pgno=${page }&key=&word=&articleno=#">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${param.pgno + 1 <= lastpage }">
					<li class="page-item"><a class="page-link" href="${root }/board/list?pgno=${param.pgno + 1}&key=&word=">다음</a></li>
				</c:if>
				<c:if test="${param.pgno + 1 > lastpage}">
					<li class="page-item"><a class="page-link" href="#" onClick="alert('다음 페이지가 없습니다.')" >다음</a></li>
				</c:if>
			</ul>
		</div>
      </div>
    <form id="form-no-param" method="get" action="${root}/board/detail">
      <input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
      <input type="hidden" id="key" name="key" value="${param.key}">
      <input type="hidden" id="word" name="word" value="${param.word}">
      <input type="hidden" id="articleno" name="articleno" value="">
    </form>
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
       	  document.querySelector("#pgno").value = "${param.pgno}";
       	  document.querySelector("#key").value = "${param.key}";
       	  document.querySelector("#word").value = "${param.word}"; 
          document.querySelector("#articleno").value = this.getAttribute("data-no");
          document.querySelector("#form-no-param").submit();
        });
      });
      
      if(document.querySelector("#btn-mv-register")){
      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board/mvwrite";
      });}
      
      if(document.querySelector("#btn-search")){
    	  document.querySelector("#btn-search").addEventListener("click", function () {
        	  let form = document.querySelector("#form-search");
              form.setAttribute("action", "${root}/board/list");
              form.submit();
          }); 
      }
    </script>
<%@ include file="../common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
