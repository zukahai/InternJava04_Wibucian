<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Menu | Quản lí quán cà phê</title>
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
            <div class="kf-parallax-bg js-parallax" style="background-image: url(/user/images/service3.jpg);"></div>
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
                        Choose Best of
                    </div>
                    <h3 class="kf-title element-anim-1 scroll-animate" data-animate="active">
                        Kaffen Coffee Menu
                    </h3>
                </div>

                <div class="kf-filter kf-filter-menu element-anim-1 scroll-animate" data-animate="active">
                    <a href="#" data-href=".all" class="active">All</a>
                    <a href="#" data-href=".fast-food">Fast food</a>
                    <a href="#" data-href=".hot-coffee">Hot coffee</a>
                    <a href="#" data-href=".dessert">Dessert</a>
                </div>

                <div class="kf-menu-items" style="background-image: url(user/images/menu_logo.png);">
                    <div class="row">

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all hot-coffee">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu1.jpg" class="has-popup-image"><img src="user/images/menu1.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Americano</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$4.9</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all hot-coffee">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu2.jpg" class="has-popup-image"><img src="user/images/menu2.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Espresso</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$4.9</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all dessert">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu3.jpg" class="has-popup-image"><img src="user/images/menu3.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Barista Pouring Syrup</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$3.5</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all hot-coffee">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu4.jpg" class="has-popup-image"><img src="user/images/menu4.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Late</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$6.0</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all dessert">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu5.jpg" class="has-popup-image"><img src="user/images/menu5.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Cappuccino Arabica</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$2.8</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all dessert">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/menu6.jpg" class="has-popup-image"><img src="user/images/menu6.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Milk Cream</h5>
                                    <div class="subname">2/3 espresso, 1/3 streamed milk</div>
                                    <div class="price">$7.5</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all fast-food">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/ins_gal1.jpg" class="has-popup-image"><img src="user/images/ins_gal1.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Italian Burger</h5>
                                    <div class="subname">Tomatoes, italian sausage, ground, onions, cheese</div>
                                    <div class="price">$12.8</div>
                                </div>
                            </div>
                        </div>

                        <div class="kf-menu-item-col col-xs-12 col-sm-12 col-md-12 col-lg-6 all fast-food">
                            <div class="kf-menu-item element-anim-1 scroll-animate" data-animate="active">
                                <div class="image kf-image-hover">
                                    <a href="user/images/ins_gal3.jpg" class="has-popup-image"><img src="user/images/ins_gal3.jpg" alt="" /></a>
                                </div>
                                <div class="desc">
                                    <h5 class="name">Chicken Burger</h5>
                                    <div class="subname">Ground chicken breast, mustard, paprika, onion</div>
                                    <div class="price">$17.5</div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </section>

        <!-- Section Reservation -->
        <section class="section kf-reservation kf-section-no-margin">
            <div class="container">

                <div class="kf-reservation-form element-anim-1 scroll-animate" data-animate="active">

                    <div class="image-left" style="background-image: url(user/images/reservation5.jpg);"></div>
                    <div class="image-right" style="background-image: url(user/images/reservation6.jpg);"></div>

                    <div class="kf-titles align-center">
                        <div class="kf-subtitle">
                            Booking Table
                        </div>
                        <h3 class="kf-title">
                            Make Your Reservation
                        </h3>
                    </div>

                    <form id="rform" method="post">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <input type="text" name="name" placeholder="Full Name" />
                                    <i class="far fa-user"></i>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <input type="email" name="email" placeholder="Email Address" />
                                    <i class="fas fa-at"></i>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <input type="tel" name="tel" placeholder="Phone Number" />
                                    <i class="fas fa-mobile-alt"></i>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <select name="persons">
                                        <option>1 Person</option>
                                        <option>2 Persons</option>
                                        <option>3 Persons</option>
                                        <option>4 Persons</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <input type="date" name="date" />
                                    <i class="far fa-calendar-alt"></i>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                <div class="kf-field">
                                    <input type="text" name="time" placeholder="Time" />
                                    <i class="far fa-clock"></i>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="kf-bts">
                                    <a href="#" class="kf-btn" onclick="$('#rform').submit(); return false;">
                                        <span>booking table</span>
                                        <i class="fas fa-chevron-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="alert-success" style="display: none;">
                        <p>Thanks, your message is sent successfully.</p>
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