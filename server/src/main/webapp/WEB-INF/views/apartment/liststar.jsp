<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<c:if test="${empty userinfo}">
	<script type="text/javascript">
		alert("로그인 후 이용 가능한 페이지입니다.");
		location.href = "${root}/user/login";
	</script>
</c:if>

<div class="container">
	<div class="row justify-content-center">
	<div style="height: 70px"></div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="sky">관심지역</mark>
			</h2>
		</div>
		<div class="mt-3"></div>

		<form id="form-add" method="POST" action="">
			<input type="hidden" name="act" value="addstar" />

			<div class="row col-md-12 justify-content-center mb-2">
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" name="sido"
						id="sido">
						<option value="">시도선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" name="gugun "
						id="gugun">
						<option value="">구군선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" name="dong"
						id="dong">
						<option value="">동선택</option>
					</select>
				</div>
				<!-- <div class="form-group col-md-2">
					<select class="form-select bg-dark text-light" name="year"
						id="year">
						<option value="">매매년선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-dark text-light" name="month"
						id="month">
						<option value="">매매월선택</option>
					</select>
				</div> -->
				<div class="form-group col-md-2">
					<button type="button" id="add-btn"
						class="btn btn-outline-primary col-md-12">추가</button>
				</div>
			</div>
		</form>

		<div style="height: 20px"></div>
		
		<div class="row">
      <div id="tablediv" class="col-md-5">
        <script type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=40d9213eaf25e5d9bab5185da885fe33&libraries=services"></script>
        <div id="_searchresult" style="width: 100%; height: 500px; overflow: scroll;">
          <table class="table table-hover text-center " >
            <tr>
              <th>아파트이름</th>
              <th>층</th>
              <th>면적</th>
              <th>법정동</th>
              <th>거래금액</th>
            </tr>
            <tbody id="aptlist">
            <c:forEach items="${hdeals}" var="hdeal">
            <tr>
              <th>${hdeal.apartmentName}</th>
              <th>층</th>
              <th>면적</th>
              <th>법정동</th>
              <th>거래금액</th>
            </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
      <div id="_mapdiv" class="col-md-7" style="position:relative;">
        <div id="map" style="width: 100%; height: 500px; float: right;"></div>
        
        <div style="position:absolute;top:15px;right:20px;z-index:1;">
    <button type="button" id="mst-btn">
              지하터널</button>
    </div>
      </div>
    </div>
    <div style="height: 20px"></div>
		<table class="table table-hover">
			<thead>
				<tr class="text-center">
					<th scope="col">번호</th>
					<th scope="col">시</th>
					<th scope="col">구</th>
					<th scope="col">동</th>
					<th scope="col">매매 년/월</th>
					<th scope="col">이동</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="star" items="${stars}" varStatus="status">
					<tr class="text-center">
						<th scope="row">${status.count}</th>
						<td>${star.sidoName}</td>
						<td>${star.gugunName}</td>
						<td>${star.dongName}</td>
						<td>${star.dealYM}</td>
						<td><a href="#" class="star-move link-dark text-center"
							data-code="${star.dongCode}" data-ym="${star.dealYM}" style="text-decoration: none">이동
						</a></td>
						<td><a href="#" class="star-delete link-dark text-center"
							data-no="${star.starNo}" style="text-decoration: none">삭제
						</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<form id="form-no-param" method="post" action="${root}/star/deletestar">
		<input type="hidden" id="starno" name="starno" value="">
	</form>
	
	
</div>

<%@ include file="../common/footer.jsp"%>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="${root}/assets/js/aptstar.js"></script>

<script>
	
	let stars = document.querySelectorAll(".star-delete");
	stars.forEach(function (star) {
		star.addEventListener("click", function () {
		//alert(this.getAttribute("data-no"));
        document.querySelector("#starno").value = this.getAttribute("data-no");
        console.log(document.querySelector("#starno").value);
        document.querySelector("#form-no-param").submit();
      });
    });
	
	document.querySelector("#add-btn").addEventListener("click", function () {
		let dongSel = document.querySelector("#dong");
		  let dongCode = dongSel[dongSel.selectedIndex].value;
		  //alert("dongCode : "+ dongCode );
		  
		  let form = document.querySelector("#form-add");
		  //console.log(form.querySelector("#dong").value);
          
		  form.setAttribute("action", "${root}/star/addstar?dongCode=${dongCode}");
          form.submit();
	});
	</script>