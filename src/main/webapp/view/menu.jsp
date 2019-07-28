<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TweenMax.min.js"></script>
<script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>
<head>
<style type="text/css">
html {
  font-family: -apple-system, BlinkMacSystemFont,
    "Segoe UI", "Roboto", "Oxygen",
    "Ubuntu", "Cantarell", "Fira Sans",
    "Droid Sans", "Helvetica Neue", sans-serif;
  font-size: calc(18px + 1vh);
  height: 100%;
}

body {
  background: #111;
  color: #fff;
  display: flex;
  line-height: 1;
  margin: 0;
  min-height: 100%;
  overflow-x: hidden;
}

.Menu {
  display: flex;
  flex-direction: column;
  list-style: none;
  margin: 0;
  padding: 0;
  width: 100%;
}

.Menu-item {
  display: flex;
  flex: 100%;
}

.Menu-item-link {
  align-items: center;
  color: #fff;
  display: flex;
  justify-content: space-between;
  padding: 1.25rem 1.5rem;
  text-decoration: none;
  touch-action: manipulation;
  transition: filter 0.25s cubic-bezier(1, 0, 0, 1);
  width: 100%;
  -webkit-tap-highlight-color: transparent;
}

.Menu-item-link:active {
  filter: brightness(1.2);
}

.Menu-item:nth-child(5n+1) .Menu-item-link {
  background-color: #4B6C96;
  color: #d3dff1;
}

.Menu-item:nth-child(5n+2) .Menu-item-link {
  background-color: #DFDBC9;
  color: #656048;
}

.Menu-item:nth-child(5n+3) .Menu-item-link {
  background-color: #9FC7CC;
  color: #496063;
}

.Menu-item:nth-child(5n+4) .Menu-item-link {
  background-color: #9B8DAA;
  color: #f2e5ff;
}

.Menu-item:nth-child(5n+5) .Menu-item-link {
  background-color: #D1535D;
  color: #ffd9dc;
}

.Menu-item-link::after {
  border: solid currentColor;
  border-width: 0.125rem 0.125rem 0 0;
  content: "";
  height: 0.5rem;
  transform: rotate(45deg);
  width: 0.5rem;
}
body {
  background-image: url("https://png.pngtree.com/thumb_back/fw800/back_pic/04/55/96/305864ee4494d9f.JPG");
}
</style>
<script type="text/javascript">
var elements = document.querySelectorAll('.js-MenuItem');
var duration = 0.5;
var delay = duration / elements.length;

elements.forEach((element, index) => {
  var elementsByDistance = [];
  
  elements.forEach((thisElement, thisIndex) => {
    var distance = Math.abs(index - thisIndex);
    
    if (!elementsByDistance[distance]) {
      elementsByDistance[distance] = [];
    }
    
    elementsByDistance[distance].push(thisElement);
  });
  
  element.addEventListener('click', event => {
    var timeline = new TimelineMax();
    event.preventDefault();
    timeline.staggerFromTo(elementsByDistance, duration, {
      x: '0%'
    }, {
      x: '-100%',
      ease: Back.easeIn.config(1)
    }, delay);
    timeline.staggerFromTo(elementsByDistance, duration, {
      x: '100%'
    }, {
      x: '0%',
      ease: Back.easeOut.config(1)
    }, delay);
  });
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="">





	<ul class="Menu">
  <li class="Menu-item js-MenuItem">
    <a class="Menu-item-link" href="gotoQuery">
      Add Query
    </a>
  </li>
  <li class="Menu-item js-MenuItem">
    <a class="Menu-item-link" href="gotoQueryReport">
      Query Report
    </a>
  </li>
  <li class="Menu-item js-MenuItem">
    <a class="Menu-item-link" href="gotoAddClient">
      Add Client
    </a>
  </li>
  <li class="Menu-item js-MenuItem">
    <a class="Menu-item-link" href="gotoAddEmployee">
      Add Employee
    </a>
  </li>
  <li class="Menu-item js-MenuItem">
    <a class="Menu-item-link" href="updateExcel">
      Update Excel
    </a>
  </li>
</ul>
	</form>

</body>
</html>