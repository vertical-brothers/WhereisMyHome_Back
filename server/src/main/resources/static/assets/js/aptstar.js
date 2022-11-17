var map;
let date = new Date();

window.onload = function () {
//  let yearEl = document.querySelector("#year");
//  let yearOpt = `<option value="">매매년도선택</option>`;
//  let year = date.getFullYear();
//  for (let i = year; i > year - 20; i--) {
//    yearOpt += `<option value="${i}">${i}년</option>`;
//  }
//  yearEl.innerHTML = yearOpt;
  // 브라우저가 열리면 시도정보 얻기.
  sendRequest("sido", "*00000000");
};

  
  // 카카오 지도 설정.
  var container = document.getElementById("map"); // 지도를 담을 영역의 DOM 레퍼런스
  var options = {
    // 지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표. (멀티캠퍼스)
    level: 3, // 지도의 레벨(확대, 축소 정도)
  };

  map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
  
  // 마커가 표시될 위치입니다
  var geocoder = new kakao.maps.services.Geocoder();
  



//document.querySelector("#year").addEventListener("change", function () {
//  let month = date.getMonth() + 1;
//  let monthEl = document.querySelector("#month");
//  let monthOpt = `<option value="">매매월선택</option>`;
//  let yearSel = document.querySelector("#year");
//  let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
//  for (let i = 1; i < m; i++) {
//    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
//  }
//  monthEl.innerHTML = monthOpt;
//});


// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugun", regcode);
  } else {
    initOption("gugun");
    initOption("dong");
  }
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dong", regcode);
  } else {
    initOption("dong");
  }
});

function sendRequest(selid, regcode) {
  const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
  fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  switch (selid) {
    case "sido":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `
          <option value="${regcode.code}">${regcode.name}</option>
          `;
      });
      break;
    case "gugun":
      opt += `<option value="">구군선택</option>`;
      for (let i = 0; i < data.regcodes.length; i++) {
        if (i != data.regcodes.length - 1) {
          if (
            data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
            data.regcodes[i].name.split(" ").length !=
            data.regcodes[i + 1].name.split(" ").length
          ) {
            data.regcodes.splice(i, 1);
            i--;
          }
        }
      }
      let name = "";
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
        else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
        opt += `
          <option value="${regcode.code}">${name}</option>
          `;
      });
      break;
    case "dong":
      opt += `<option value="">동선택</option>`;
      let idx = 2;
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length != 3) idx = 3;
        opt += `
          <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
          `;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  options.length = 0;
  // let len = options.length;
  // for (let i = len - 1; i >= 0; i--) {
  // options.remove(i);
  // }
}

///////////////////////// 아파트 매매 정보 /////////////////////////
let stars_move = document.querySelectorAll(".star-move");
stars_move.forEach(function (star_move) {
	star_move.addEventListener("click", function () {
		  let url =
			    "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
			  let dongCode = this.getAttribute("data-code");
			  let dealYM = this.getAttribute("data-ym");
			  console.log(dongCode+" "+dealYM);
			  let queryParams = "/whereismyhome-db-15/api?"
//			    "regCode=" + encodeURIComponent(regCode); /*아파트소재 구군*/
			  
			//  let gugunSel = document.querySelector("#gugun");
			  
			  queryParams +=
				    encodeURIComponent("dong") + "=" + encodeURIComponent(dongCode);
			  queryParams +=
			    "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(dealYM); /*조회년월*/
			//  queryParams +=
//			    "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /*페이지번호*/
			//  queryParams +=
//			    "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("1000"); /*페이지당건수*/


			//  location.href=queryParams;
			  
			  hideMarkers();
			  hideCustomOverlays2();
			  hidePolylines();
			  hideCustomOverlays();
			  
			  fetch(`http://localhost:80/whereismyhome/house/search?dongCode=${dongCode}&apartmentName=`)
			  	.then((response) => response.json())
			  	.then((data) => dataSet(data));
			});
} );

///////////////////////// 지하터널 보기 /////////////////////////
document.querySelector("#mst-btn").addEventListener("click", function () {
	hideCustomOverlays2();
	doMst();
});

function dataSet(data){
	makeList(data);
	drawMap(data);
	getList(data);
}

// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markers = [];
// 지도에 표시된 인포윈도우 객체를 가지고 있을 배열
var customOverlays2 = [];
var polylines = [];
var customOverlays = [];
var apt_map;

function drawMap(data){
	
//  let parser = new DOMParser();
//  const xml = parser.parseFromString(data, "application/xml");
//  let apts = xml.querySelectorAll("item");
  
  apt_map = new Map();
  
  data.forEach((apt)=>{
    let address = "";
    address += `${apt.roadName}`
    address += (" ")
    address += `${apt.roadNamebonbun}`;
        
    let price = `${apt.dealAmount}`;

    geocoder.addressSearch(address, function(result, status) {
      // 정상적으로 검색이 완료됐으면
       if (status === kakao.maps.services.Status.OK) {


          coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
              // map: map,
              position: coords
          });
          
          // 마커가 지도 위에 표시되도록 설정합니다
          marker.setMap(map);
          
          // 생성된 마커를 배열에 추가합니다
          markers.push(marker);
          
          
       if(!apt_map.has(`${apt.aptName}`)){
      		apt_map.set(`${apt.aptName}`,1);
       }
       else{
      		apt_map.set(`${apt.aptName}`,apt_map.get(`${apt.aptName}`)+1);
       }
   	   //나중에 처음 생성될때만 만들고 그 다음부터는 오버레이.setContent(content)로 값 변경 하게 했으면 좋겠다.
       let price_count = apt_map.get(`${apt.aptName}`);
        
   	   
       // 커스텀 오버레이에 표시할 내용입니다     
  		// HTML 문자열 또는 Dom Element 입니다 
  		var content = `<div class ="price-label"><span class="left"></span><span class="center">${price_count}건</span><span class="right"></span></div>`;

  		// 커스텀 오버레이가 표시될 위치입니다 
  		var position = coords;
  		
  		// 커스텀 오버레이를 생성합니다
  		var customOverlay = new kakao.maps.CustomOverlay({
  		    position: position,
  		    content: content   
  		});
  		customOverlays2.push(customOverlay);

  		// 커스텀 오버레이를 지도에 표시합니다
  		customOverlay.setMap(map);
          
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
      } 
    });
  });
}

let set;
let apt_info;

function getList(data){
	
//	  let parser = new DOMParser();
//	  const xml = parser.parseFromString(data, "application/xml");
//	  let apts = xml.querySelectorAll("item");
	  
	  set = new Set();
	  apt_info = [];
	  
	  data.forEach((apt)=>{
		  let address = "";
		  address += `${apt.roadName}`
		  address += (" ")
		  address += `${apt.roadNamebonbun}`;
		    
		  let price = `${apt.dealAmount}`;

	    geocoder.addressSearch(address, function(result, status) {
	      // 정상적으로 검색이 완료됐으면
	       if (status === kakao.maps.services.Status.OK) {
	    	   
	    	   if(!set.has(`${apt.aptName}`)){ // mst용
		        	  set.add(`${apt.aptName}`);
		        	  apt_info.push([`${apt.aptName}`,result[0].y, result[0].x]);
		          }

	      } 
	    });
	  });	  
	}
let parents;

//async function doMst(){
function doMst(){
	console.time("calculatingTime");
	console.log(apt_info, apt_info.length , "eee");
	
	let apt_dis=[];
	  
	for(let i=0;i<apt_info.length-1;i++){
		for(let j=i+1;j<apt_info.length;j++){
				let d = getDistance(apt_info[i][1],apt_info[i][2],apt_info[j][1],apt_info[j][2]);
				apt_dis.push([apt_info[i][0],i, apt_info[j][0],j, d]);
		}
	}	  
	//await new Promise(resolve => setTimeout(resolve, 500));
  
	apt_dis.sort((a,b)=>{return (a[4]-b[4])});
	
	console.log(apt_dis, apt_dis.length, "sss"); //sort 완료
	
	parents = Array.from({ length: apt_info.length },() => -1);
	parents.map((_, i) => { parents[i] = i });
	
	console.log(parents, parents.length, "www"); 
	
	
	let cnt = 0;
	let answer = 0;
	let result = [];
    for(let edge of apt_dis){
        if (Union(edge[1], edge[3])) {
        	console.log("check");
        	result.push([apt_info[edge[1]], apt_info[edge[3]], edge[4]]);
            answer+=edge[4];
            cnt++;
            if(cnt==apt_info.length-1)
                break;
        }
    }       
    console.log(result);
	console.log(answer, "sss");  
	
	console.timeEnd("calculatingTime");
	
	for(let edge of result){
		// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
		var linePath = [
		    new kakao.maps.LatLng(edge[0][1], edge[0][2]),
		    new kakao.maps.LatLng(edge[1][1], edge[1][2])
		];
		// 지도에 표시할 선을 생성합니다
		var polyline = new kakao.maps.Polyline({
		    path: linePath, // 선을 구성하는 좌표배열 입니다
		    strokeWeight: 5, // 선의 두께 입니다
		    strokeColor: '#FFAE00', // 선의 색깔입니다
		    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'solid' // 선의 스타일입니다
		});
		polylines.push(polyline);
		// 지도에 선을 표시합니다 
		polyline.setMap(map);


		// 커스텀 오버레이에 표시할 내용입니다     
		// HTML 문자열 또는 Dom Element 입니다 
		var content = '<div class ="mst-label"><span class="left"></span><span class="center">'+edge[2]+'m</span><span class="right"></span></div>';

		// 커스텀 오버레이가 표시될 위치입니다 
		var position = new kakao.maps.LatLng(String((Number(edge[0][1])+Number(edge[1][1]))/2), String((Number(edge[0][2])+Number(edge[1][2]))/2)); 
		
		// 커스텀 오버레이를 생성합니다
		var customOverlay = new kakao.maps.CustomOverlay({
		    position: position,
		    content: content   
		});
		customOverlays.push(customOverlay);

		// 커스텀 오버레이를 지도에 표시합니다
		customOverlay.setMap(map);
	}
	  
}

function Union(a,b){
	let aRoot = find(a);
	let bRoot = find(b);
	
	if(aRoot===bRoot)
		return false;
	
	if(aRoot>bRoot) 
        parents[aRoot]=bRoot;
    else
        parents[bRoot]=aRoot;
     
    return true;
}

function find(x){
	if(parents[x]===x)
		return x;
	return find(parents[x]);
}

function hideCustomOverlays2(){
	for (var i = 0; i < customOverlays2.length; i++) {
		customOverlays2[i].setMap(null);
    }  
}
function hideMarkers() {
	for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }     
}
function hidePolylines(){
	for (var i = 0; i < polylines.length; i++) {
		polylines[i].setMap(null);
    }  
}
function hideCustomOverlays(){
	for (var i = 0; i < customOverlays.length; i++) {
		customOverlays[i].setMap(null);
    }  
}

function getDistance(lat1, lon1, lat2, lon2) {
    if ((lat1 == lat2) && (lon1 == lon2))
        return 0;

    var radLat1 = Math.PI * lat1 / 180;
    var radLat2 = Math.PI * lat2 / 180;
    var theta = lon1 - lon2;
    var radTheta = Math.PI * theta / 180;
    var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
    if (dist > 1)
        dist = 1;

    dist = Math.acos(dist);
    dist = dist * 180 / Math.PI;
    dist = dist * 60 * 1.1515 * 1.609344 * 1000;
    

    //if (dist < 100) dist = Math.round(dist / 10) * 10;
    //else dist = Math.round(dist / 100) * 100;
    dist = Math.round(dist*10)/10;
    
    console.log(dist);
    return dist;
}
  

function makeList(data) {
	document.querySelector("table").setAttribute("style", "display: ;");
	  let tbody = document.querySelector("#aptlist");
	  console.log("makeList");
	  initTable();
	  data.forEach(function(apt){
		  let tr = document.createElement("tr");

		    let nameTd = document.createElement("td");
		    nameTd.appendChild(document.createTextNode(`${apt.aptName}`));
		    tr.appendChild(nameTd);

		    let floorTd = document.createElement("td");
		    floorTd.appendChild(document.createTextNode(`${apt.floor}`));
		    tr.appendChild(floorTd);

		    let areaTd = document.createElement("td");
		    areaTd.appendChild(document.createTextNode(`${apt.area}`));
		    tr.appendChild(areaTd);

		    let dongTd = document.createElement("td");
		    dongTd.appendChild(document.createTextNode(`${apt.dongName}`));
		    tr.appendChild(dongTd);
		    
		    let priceTd = document.createElement("td");
		    priceTd.appendChild(document.createTextNode(`${apt.dealAmount}`));
		    tr.appendChild(priceTd);

		    tbody.appendChild(tr);
	  });
}

function initTable() {
  let tbody = document.querySelector("#aptlist");
  let len = tbody.rows.length;
  for (let i = len - 1; i >= 0; i--) {
    tbody.deleteRow(i);
  }
}

      