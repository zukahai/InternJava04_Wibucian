<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Menu | Wibucian</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Merienda:wght@400;700&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Oswald:wght@200;300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- BEGIN CSS STYLES -->
    <link rel="stylesheet" href="user/styles/bootstrap.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/fonts/font-awesome/css/font-awesome.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/fonts/font-awesome/css/line-awesome.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/animate.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/magnific-popup.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/splitting.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/swiper.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/style.css" type="text/css" media="all" />
    <!-- END CSS STYLES -->

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<body>
<div class="bg">

    <!-- Preloader -->
    <div class="preloader">
        <div class="centrize full-width">
            <div class="vertical-center">
                <div class="spinner-logo">
                    <img src="user/images/logo.png" alt="" />
                    <div class="spinner-dot">
                        <div class="spinner-line"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="menu.jsp"></jsp:include>
    <!-- Wrapper -->
    <div class="wrapper">

        <!-- Section Started Inner -->
        <section class="section kf-started-inner">
            <div class="kf-parallax-bg js-parallax" style="background-image: url(/user/images/service3.png);"></div>
            <div class="container">

                <h1 class="kf-h-title text-anim-1 scroll-animate" data-splitting="chars" data-animate="active">
                    Coffee Menu
                </h1>

            </div>
        </section>

        <!-- Section Menu -->
        <section class="section kf-menu kf-menu-tabs">
            <div class="container">

                <div class="kf-titles align-center">
                    <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                        Giải khát cùng
                    </div>
                    <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                        Wibucian
                    </h3>
                </div>

                <div class="kf-filter kf-filter-menu element-anim-1 scroll-animate" data-animate="active">
                    <a href="#" data-href=".all" class="active">All</a>
                    <c:forEach var="item" items="${typetables}">
                        <a href="#" data-href=".${item.id}">${item.productName}</a>
                    </c:forEach>
                </div>

                <div class="kf-menu-items" style="background-image: url(user/images/menu_logo.png);">
                    <div class="row">

                        <c:forEach var="item" items="${products}">
                            <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all ${item.productType.id}">
                                <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image kf-image-hover">
                                        <a href="admin/assets/file-upload/${item.srcImage}" class="has-popup-image"><img src="admin/assets/file-upload/${item.srcImage}" alt="" /></a>
                                    </div>
                                    <div class="desc">
                                        <h5 class="name">${item.productName}</h5>
                                        <div class="subname">${item.getIngredient()}</div>
                                        <c:if test="${item.getPercentSale() != null}">
                                        <span class="discount">Giảm giá ${item.getPercentSale()}%</span>
                                        </c:if>
                                        <div class="price"><fmt:formatNumber value="${ item.price}" maxFractionDigits = "3" type="number"/>VND</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>
        </section>

        <!-- Section Reservation -->
        <section class="section kf-reservation kf-section-no-margin">
            <div class="container">

            </div>
        </section>

        <!-- Section Brands -->
        <div class="section kf-brands">
            <div class="container">

                <div class="kf-brands-items row">

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand1.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand2.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand3.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand4.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand5.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
                        <div class="kf-brands-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="image">
                                <img src="user/images/brand6.png" alt="" />
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </div>

    <jsp:include page="footer.jsp"></jsp:include>

</div>

<!-- Scripts -->
<script src="user/js/jquery.min.js"></script>
<script src="user/js/jquery.validate.min.js"></script>
<script src="user/js/bootstrap.js"></script>
<script src="user/js/swiper.js"></script>
<script src="user/js/splitting.js"></script>
<script src="user/js/jquery.paroller.min.js"></script>
<script src="user/js/parallax.js"></script>
<script src="user/js/magnific-popup.js"></script>
<script src="user/js/imagesloaded.pkgd.js"></script>
<script src="user/js/isotope.pkgd.js"></script>
<script src="user/js/jquery.scrolla.js"></script>
<script src="user/js/skrollr.js"></script>
<script src="user/js/common.js"></script>
</body>

</html>
