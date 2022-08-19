<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="error" value="${requestScope.error}"/>
<!--
Author: Keenthemes
Product Name: Metronic - Bootstrap 5 HTML, VueJS, React, Angular, Asp.Net Core, Blazor, Django, Flask & Laravel Admin Dashboard Theme
Purchase: https://1.envato.market/EA4JP
Website: http://www.keenthemes.com
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Dribbble: www.dribbble.com/keenthemes
Like: www.facebook.com/keenthemes
License: For each use you must have a valid license purchased only from above link in order to legally use the theme for your project.
-->
<html lang="en">
	<!--begin::Head-->
	<head>
		<base href="../../../">
		<title>Login - Wibucian</title>
		<meta charset="utf-8"/>
		<meta name="description"
			  content="The most advanced Bootstrap Admin Theme on Themeforest trusted by 100,000 beginners and professionals. Multi-demo, Dark Mode, RTL support and complete React, Angular, Vue, Asp.Net Core, Blazor, Django, Flask &amp; Laravel versions. Grab your copy now and get life-time updates for free."/>
		<meta name="keywords"
			  content="Metronic, Bootstrap, Bootstrap 5, Angular, VueJs, React, Asp.Net Core, Blazor, Django, Flask &amp; Laravel, admin themes, web design, figma, web development, free templates, free admin themes, bootstrap theme, bootstrap template, bootstrap dashboard, bootstrap dak mode, bootstrap button, bootstrap datepicker, bootstrap timepicker, fullcalendar, datatables, flaticon"/>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<meta property="og:locale" content="en_US"/>
		<meta property="og:type" content="article"/>
		<meta property="og:title"
			  content="Metronic - Bootstrap 5 HTML, VueJS, React, Angular, Asp.Net Core, Blazor, Django, Flask &amp; Laravel Admin Dashboard Theme"/>
		<meta property="og:url" content="https://keenthemes.com/metronic"/>
		<meta property="og:site_name" content="Keenthemes | Metronic"/>
		<link rel="canonical" href="https://preview.keenthemes.com/metronic8"/>
		<link rel="shortcut icon" href="/admin/assets/media/logos/favicon.ico"/>
		<!--begin::Fonts-->
		<link rel="stylesheet"
			  href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700"/>
		<!--end::Fonts-->
		<!--begin::Global Stylesheets Bundle(used by all pages)-->
		<link href="/admin/assets/plugins/global/plugins.bundle.css" rel="stylesheet"
			  type="text/css"/>
		<link href="/admin/assets/css/style.bundle.css" rel="stylesheet" type="text/css"/>
		<!--end::Global Stylesheets Bundle-->
	</head>
	<!--end::Head-->
	<!--begin::Body-->
	<body data-kt-name="metronic" id="kt_body"
		  class="app-blank bgi-size-cover bgi-position-center bgi-no-repeat">
		<!--begin::Theme mode setup on page load-->
		<script>if (document.documentElement) {
            const defaultThemeMode = "system";
            const name = document.body.getAttribute("data-kt-name");
            let themeMode = localStorage.getItem("kt_" + (
                name !== null ? name + "_" : "") + "theme_mode_value");
            if (themeMode === null) {
                if (defaultThemeMode === "system") {
                    themeMode =
                        window.matchMedia("(prefers-color-scheme: dark)").matches ?
                            "dark" : "light";
                } else {
                    themeMode = defaultThemeMode;
                }
            }
            document.documentElement.setAttribute("data-theme", themeMode);
        }</script>
		<!--end::Theme mode setup on page load-->
		<!--begin::Main-->
		<!--begin::Root-->
		<div class="d-flex flex-column flex-root">
			<!--begin::Page bg image-->
			<style>body {
                background-image: url('/admin/assets/img/cafe.png');
            }

            [data-theme="dark"] body {
                background-image: url('/admin/assets/img/cafe.png');
            }</style>
			<!--end::Page bg image-->
			<!--begin::Authentication - Sign-in -->
			<div class="d-flex flex-column flex-column-fluid flex-lg-row">
				<!--begin::Aside-->
				<div class="d-flex flex-center w-lg-50 pt-15 pt-lg-0 px-10">
					<!--begin::Aside-->
					<!--begin::Aside-->
				</div>
				<!--begin::Aside-->
				<!--begin::Body-->
				<div class="d-flex flex-center w-lg-50 p-10">
					<!--begin::Card-->
					<div class="card rounded-3 w-md-550px">
						<!--begin::Card body-->
						<div class="card-body p-10 p-lg-20">
							<!--begin::Form-->
							<form class="form w-100" novalidate="novalidate"
								  id="kt_sign_in_form"
								  data-kt-redirect-url="../../demo14/dist/index.html"
								  action="/login" method="post">
								<!--begin::Heading-->
								<div class="text-center mb-11">
									<!--begin::Title-->
									<h1 class="text-dark fw-bolder mb-3">Đăng nhập</h1>
									<!--end::Title-->
									<!--begin::Subtitle-->
									<%--									<div class="text-gray-500 fw-semibold fs-6">Your--%>
									<%--										Social Campaigns--%>
									<%--									</div>--%>
									<!--end::Subtitle=-->
								</div>
								<!--begin::Heading-->
								<!--begin::Login options-->
								
								<!--end::Login options-->
								<!--begin::Separator-->
								<div class="separator separator-content my-14">
									<span class="w-125px text-gray-500 fw-semibold fs-7"></span>
								</div>
								<!--end::Separator-->
								<!--begin::Input group=-->
								<div class="fv-row mb-8">
									<!--begin::Email-->
									<input type="text" placeholder="Username"
										   name="username" autocomplete="off"
										   class="form-control bg-transparent" required/>
									<!--end::Email-->
								</div>
								<!--end::Input group=-->
								<div class="fv-row mb-3">
									<!--begin::Password-->
									<input type="password" placeholder="Password"
										   name="password" autocomplete="off"
										   class="form-control bg-transparent" required/>
									<!--end::Password-->
								</div>
								
								<%--								<div>${param.error}</div>--%>
								
								<div class="fv-row mb-3">
									<!--begin::Password-->
									<c:if test="${not empty param.error}">
										<div class="text-center text-danger mt-10">Sai tên
											đăng nhập hoặc mật khẩu
										</div>
									</c:if>
									<!--end::Password-->
								</div>
								
								<!--end::Input group=-->
								<!--begin::Wrapper-->
								<div class="d-flex flex-stack flex-wrap gap-3 fs-base fw-semibold mb-8">
									<div></div>
									<%--									<!--begin::Link-->--%>
									<%--									<a href="../../demo14/dist/authentication/layouts/creative/reset-password.html"--%>
									<%--									   class="link-primary">Forgot Password ?--%>
									<%--									</a>--%>
									<!--end::Link-->
								</div>
								<!--end::Wrapper-->
								<!--begin::Submit button-->
								<div class="d-grid mb-10">
									<button type="submit" id="kt_sign_in_submit"
											class="btn btn-primary">
										<!--begin::Indicator label-->
										<span class="indicator-label">Đăng nhập</span>
										<!--end::Indicator label-->
										<!--begin::Indicator progress-->
										<span class="indicator-progress">Please wait...
										<span class="spinner-border spinner-border-sm align-middle ms-2"></span></span>
										<!--end::Indicator progress-->
									</button>
								</div>
								<!--end::Submit button-->
								<!--begin::Sign up-->
								
								<!--end::Sign up-->
							</form>
							<!--end::Form-->
						</div>
						<!--end::Card body-->
					</div>
					<!--end::Card-->
				</div>
				<!--end::Body-->
			</div>
			<!--end::Authentication - Sign-in-->
		</div>
		<!--end::Root-->
		<!--end::Main-->
		<!--begin::Javascript-->
		<script>var hostUrl = "/admin/assets/";</script>
		<!--begin::Global Javascript Bundle(used by all pages)-->
		<script src="/admin/assets/plugins/global/plugins.bundle.js"></script>
		<script src="/admin/assets/js/scripts.bundle.js"></script>
		<!--end::Global Javascript Bundle-->
		<!--begin::Custom Javascript(used by this page)-->
		<%--		<script src="/admin/assets/js/custom/authentication/sign-in/general.js"></script>--%>
		<!--end::Custom Javascript-->
		<!--end::Javascript-->
	</body>
	<!--end::Body-->
</html>