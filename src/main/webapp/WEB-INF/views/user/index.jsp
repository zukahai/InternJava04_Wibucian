<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Wibucian</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Merienda:wght@400;700&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Oswald:wght@200;300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- BEGIN CSS STYLES -->
    <link rel="stylesheet" href="user/styles/bootstrap.css" type="text/css" media="all" />
    <link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.css" type="text/css" media="all" />
    <link rel="stylesheet" href="fonts/font-awesome/css/line-awesome.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/animate.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/magnific-popup.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/splitting.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/styles/swiper.css" type="text/css" media="all" />
    <link rel="stylesheet" href="user/style.css" type="text/css" media="all" />
    <!-- END CSS STYLES -->

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

        <!-- Section Started Slider -->
        <section class="section kf-started-slider">
            <div class="swiper-container">
                <div class="swiper-wrapper">

                    <div class="swiper-slide">
                        <div class="kf-started-item">
                            <div class="slide js-parallax" style="background-image: url(admin/assets/file-upload/caphe.jpg);"></div>
                            <div class="container">
                                <div class="description align-left element-anim-1">
                                    <div class="subtitles">
                                        Chào mừng đến với Wibucian
                                    </div>
                                    <h2 class="name text-anim-1" data-splitting="chars">
                                        Thơm ngon <br>Đậm đà
                                    </h2>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="swiper-slide">

                        <div class="kf-started-item">
                            <div class="slide js-parallax" style="background-image: url(user/images/matlanh.jpg);"></div>
                            <div class="container">
                                <div class="description align-left element-anim-1">
                                    <div class="subtitles">
                                        Chào mừng đến với Wibucian
                                    </div>
                                    <h2 class="name text-anim-1" data-splitting="chars">
                                        Giải khát với <br>Đồ uống mát lạnh
                                    </h2>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="swiper-slide">

                        <div class="kf-started-item">
                            <div class="slide js-parallax" style="background-image: url(user/images/dongchai.jpg);"></div>
                            <div class="container">
                                <div class="description align-left">
                                    <div class="subtitles">
                                        Chào mừng đến với Wibucian
                                    </div>
                                    <h2 class="name text-anim-1" data-splitting="chars">
                                        Nước uống <br>Đóng chai
                                    </h2>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>

            </div>
        </section>

        <!-- Section About -->
        <section class="section kf-about section-bg">
            <div class="container">

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-5">

                        <div class="kf-titles">
                            <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                                Về chúng tôi
                            </div>
                            <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                                Cà phê & nước uống giải khát <br> Phục vụ trực tiếp
                            </h3>
                        </div>

                        <div class="kf-text element-anim-1 scroll-animate" data-animate="active">
                            <p>
                                Cafe nếu bạn nghĩ không ngon thì nó sẽ rất đắng. Nhưng nếu thả lỏng ra và cảm nhận,
                                sau vị đắng sẽ là vị ngọt nhẹ thoáng qua. Cuộc sống của mỗi người chúng ta cũng giống như vậy!
                            </p>
                        </div>

                        <div class="kf-about-quote element-anim-1 scroll-animate" data-animate="active">
                            <img src="user/images/HaiPD7.jpg" alt="" />
                            <div>Luôn mang đến hương vị đậm đà cho người dùng</div>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-7">

                        <div class="kf-about-image element-anim-1 scroll-animate" data-animate="active">
                            <img src="user/images/matlanh.jpg" alt="" />
                        </div>

                    </div>
                </div>

            </div>
        </section>

        <!-- Section Menu -->
        <section class="section kf-menu kf-parallax" style="background-image: url(user/images/category_bg.jpg);">
            <div class="container">

                <div class="kf-titles align-center">
                    <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                        Giải khát cùng
                    </div>
                    <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                        Wibucian
                    </h3>
                </div>

                <div class="kf-menu-items" style="background-image: url(user/images/menu_logo.png);">
                    <div class="row">

                        <c:forEach var="item" items="${products}">
                            <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all ${item.productType.id}">
                                <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image kf-image-hover">
                                        <a href="${item.srcImage}" class="has-popup-image"><img src="${item.srcImage}" alt="" /></a>
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

        <!-- Section Choose -->
        <section class="section kf-choose section-bg">
            <div class="container">
                <div class="kf-parallax-icon pi-1" data-jarallax-element="-60">
                    <div class="p-icon" style="background-image: url(user/images/parallax_icon1.png);"></div>
                </div>
                <div class="kf-parallax-icon pi-2" data-jarallax-element="-80">
                    <div class="p-icon" style="background-image: url(user/images/parallax_icon2.png);"></div>
                </div>
                <div class="kf-parallax-icon pi-3" data-jarallax-element="-40">
                    <div class="p-icon" style="background-image: url(user/images/parallax_icon3.png);"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">

                        <div class="kf-choose-image element-anim-1 scroll-animate" data-animate="active">
                            <img src="user/images/caphe.jpg" alt="" />
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-5 offset-lg-1 align-self-center">

                        <div class="kf-titles">
                            <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                                TẠI SAO CHỌN CHÚNG TÔI
                            </div>
                            <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                                Cà phê Wibucian mới được thành lập để thử nghiệm đặc biệt
                            </h3>
                        </div>

                        <div class="kf-text element-anim-1 scroll-animate" data-animate="active">
                            <p>
                                Cafe nếu bạn nghĩ không ngon thì nó sẽ rất đắng. Nhưng nếu thả lỏng ra và cảm nhận,
                                sau vị đắng sẽ là vị ngọt nhẹ thoáng qua. Cuộc sống của mỗi người chúng ta cũng giống như vậy!
                            </p>
                        </div>

                        <div class="kf-choose-list">
                            <ul>
                                <li class="element-anim-1 scroll-animate" data-animate="active">
                                    <div class="icon">
                                        <img src="user/images/choose_icon1.png" alt="" />
                                    </div>
                                    <div class="desc">
                                        <h5 class="name">Hạt cà phê tự nhiên</h5>
                                        <div class="subname">Những hạt cà phê được chọn lọc từ những nhà cung cấp trong và ngoài nước</div>
                                    </div>
                                </li>
                                <li class="element-anim-1 scroll-animate" data-animate="active">
                                    <div class="icon">
                                        <img src="user/images/choose_icon2.png" alt="" />
                                    </div>
                                    <div class="desc">
                                        <h5 class="name">100% an toàn</h5>
                                        <div class="subname">Máy móc hiện đại, máy móc hiện đại, đảm bảo tạo ra những sản phẩm sạch</div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>

            </div>
        </section>
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