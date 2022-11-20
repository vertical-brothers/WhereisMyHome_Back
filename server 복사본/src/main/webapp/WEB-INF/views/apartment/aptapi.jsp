<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Where Is My Home</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
</head>

<body>

  <div class="container">
      <div class="row justify-content-center">
      <div style="height: 70px"></div>
      <div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="sky">아파트 실거래가 조회</mark>
			</h2>
		</div>

        <div class="mt-3"></div>
        <div class="row col-md-12 justify-content-center mb-2">
          <div class="form-group col-md">
            <select class="form-select bg-secondary text-light" id="sido">
              <option value="">시도선택</option>
            </select>
          </div>
          <div class="form-group col-md">
            <select class="form-select bg-secondary text-light" id="gugun">
              <option value="">구군선택</option>
            </select>
          </div>
          <div class="form-group col-md">
            <select class="form-select bg-secondary text-light" id="dong">
              <option value="">동선택</option>
            </select>
          </div>
          <!-- <div class="form-group col-md">
            <select class="form-select bg-dark text-light" id="year">
              <option value="">매매년선택</option>
            </select>
          </div>
          <div class="form-group col-md">
            <select class="form-select bg-dark text-light" id="month">
              <option value="">매매월선택</option>
            </select>
          </div> -->
          <div class="form-group col-md">
            <input class="form-control bg-dark text-light" type="text" placeholder="아파트명" id="aptName">
          </div>
          <div class="form-group col-md-2">
            <button type="button" id="list-btn" class="btn btn-outline-primary col-md-12">
              검색
            </button>
          </div>
          <div class="form-group col-md-2">
						<button type="button" id="park-btn"
							class="btn btn-outline-primary" style="color: green">주변 공원 표시</button>
		</div>
        </div>
    </div>
    
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
    
    
    
  </div>
  
  <!-- 중앙 content end -->
  <%@include file="../common/footer.jsp" %>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    <script src="${root}/assets/js/aptapi.js"></script>
    <script>
	    document.querySelector("#park-btn").addEventListener("click", function () {
    	var level = map.getLevel();
    	var latlng = map.getCenter(); 
        var message = '<p>지도 레벨은 ' + level + ' 이고</p>';
        message += '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';
        //console.log(message);
        
        let url = "${root}/park/getAllParks?lng="+latlng.getLng()+"&lat="+latlng.getLat();
      	console.log(url);
        fetch(url)
        	.then((response) => response.json())
        	.then((data) => dataSet3(data));
        
      });
    </script>
</body>

</html>