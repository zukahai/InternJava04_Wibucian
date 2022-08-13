<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Về chúng tôi | Wibucian</title>
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

        <!-- Section Started Inner -->
        <section class="section kf-started-inner">
            <div class="kf-parallax-bg js-parallax" style="background-image: url(user/images/team_inner_bg.jpg);"></div>
            <div class="container">

                <h1 class="kf-h-title text-anim-1 scroll-animate" data-splitting="chars" data-animate="active">
                    Our Chefs
                </h1>

            </div>
        </section>

        <!-- Section Team -->
        <section class="section kf-team section-bg">
            <div class="container">

                <div class="kf-titles align-center">
                    <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                        Thành viên trong nhóm
                    </div>
                    <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                        Meet Our Professional Chefs
                    </h3>
                </div>

                <div class="kf-team-items row">
                    <c:forEach var="item" items="${employees}">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
                        <div class="kf-team-item element-anim-1 scroll-animate" data-animate="active">
                            <div class="desc">
                                <h5 class="name">${item.name}</h5>
                                <div class="subname">${item.gender}</div>
                            </div>
                            <div class="image kf-image-hover">
                                <img src="${item.srcEmployee}" alt="" />
                                <div class="info">
                                    <div class="label">${item.email}</div>
                                    <div class="label">${item.phoneNumber}</div>
                                </div>
                                <div class="social">
                                    <a href="#"><i class="fa fa-facebook-square"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-linkedin"></i></a>
                                    <a href="#"><i class="fa fa-youtube"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                </div>

            </div>
        </section>

        <!-- Section CTA -->
        <section class="section kf-cta kf-parallax" style="background-image: url(user/images/cta_bg.jpg);">
            <div class="container">

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">

                        <div class="kf-titles">
                            <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                                Need a Table On Coffee House
                            </div>
                            <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                                Booking Table For Your & Family Members
                            </h3>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4 align-self-center align-right">

                        <a href="reservation.html" class="kf-btn element-anim-1 scroll-animate" data-animate="active">
                            <span>booking table</span>
                            <i class="fas fa-chevron-right"></i>
                        </a>

                    </div>
                </div>

            </div>
        </section>

        <!-- Section Testimonials Carousel -->
        <section class="section kf-testimonials kf-testimonials-2 section-bg" style="background-image: url(user/images/testimonials_bg2.jpeg);">
            <div class="container">

                <div class="kf-titles align-center">
                    <div class="kf-subtitle element-anim-1 scroll-animate" data-animate="active">
                        Customer Feedback
                    </div>
                    <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                        What Our Clients Say
                    </h3>
                </div>

                <div class="kf-testimonials-carousel">
                    <div class="swiper-container">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev1.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">Frederick S. France <em>Web Deigner</em></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev2.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">James M. London <em>Lawyer</em></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev3.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="kf-text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">Olivia D. New York <em>Dentist</em></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev1.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="kf-text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">Frederick S. France <em>Web Deigner</em></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev2.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="kf-text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">James M. London <em>Lawyer</em></h5>
                                    </div>
                                </div>

                            </div>
                            <div class="swiper-slide">

                                <div class="slide-item element-anim-1 scroll-animate" data-animate="active">
                                    <div class="image">
                                        <img src="user/images/rev3.jpg" alt="" />
                                    </div>
                                    <div class="desc">
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>
                                        <div class="kf-text">
                                            Sed ut perspiciatis unde omnis natus error luptatem accusantium doloremque laudantium totam remriam eaque quae abillo
                                        </div>
                                        <h5 class="name">Olivia D. New York <em>Dentist</em></h5>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="swiper-pagination"></div>

                    </div>
                </div>

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