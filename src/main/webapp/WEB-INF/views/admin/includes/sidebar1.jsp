<!--begin::Aside menu-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:set var="selectSubmenu"/>--%>

<div class="aside-menu flex-column-fluid px-4">
	<!--begin::Aside Menu-->
	<div
			class="hover-scroll-overlay-y my-5 pe-4 me-n4"
			id="kt_aside_menu_wrapper"
			data-kt-scroll="true"
			data-kt-scroll-activate="true"
			data-kt-scroll-height="auto"
			data-kt-scroll-dependencies="{default: '#kt_aside_footer', lg: '#kt_header, #kt_aside_footer'}"
			data-kt-scroll-wrappers="#kt_aside, #kt_aside_menu"
			data-kt-scroll-offset="{default: '5px', lg: '75px'}"
	>
		<!--begin::Menu-->
		<div
				class="menu menu-column menu-rounded menu-sub-indention fw-semibold fs-6"
				id="#kt_aside_menu"
				data-kt-menu="true"
		>
			<!--begin:Menu item-->
			<div data-kt-menu-trigger="click" class="menu-item here  menu-accordion">
				<!--begin:Menu link-->
				<span class="menu-link">
                                                <span class="menu-icon">
                                                    <i class="fas fa-house"></i>
                                                </span>
                                                <span class="menu-title">Dashboards</span>
                                                <span class="menu-arrow"></span>
                                            </span>
				<!--end:Menu link-->
				<!--begin:Menu sub-->
				<div class="menu-sub menu-sub-accordion">
				</div>
				<!--end:Menu sub-->
			</div>
			<!--end:Menu item-->
			<!--begin:Menu item-->
			<div class="menu-item pt-5">
				<!--begin:Menu content-->
				<div class="menu-content">
					<span class="menu-heading fw-bold text-uppercase fs-7">Pages</span>
				</div>
				<!--end:Menu content-->
			</div>
			<!--end:Menu item-->
			<!--begin:Menu item-->
<%--			<div data-kt-menu-trigger="click"--%>
<%--				 class="menu-item <c:if test="${selectSubmenu eq 'order'}">show</c:if> menu-accordion">--%>
<%--				<!--begin:Menu link-->--%>
<%--				<span class="menu-link">--%>
<%--                                                <span class="menu-icon">--%>
<%--                                                    <!--begin::Svg Icon | path: icons/duotune/general/gen022.svg-->--%>
<%--                                                    <span class="svg-icon svg-icon-2">--%>
<%--                                                        <svg--%>
<%--																width="24"--%>
<%--																height="24"--%>
<%--																viewBox="0 0 24 24"--%>
<%--																fill="none"--%>
<%--																xmlns="http://www.w3.org/2000/svg"--%>
<%--														>--%>
<%--                                                            <path--%>
<%--																	d="M11.2929 2.70711C11.6834 2.31658 12.3166 2.31658 12.7071 2.70711L15.2929 5.29289C15.6834 5.68342 15.6834 6.31658 15.2929 6.70711L12.7071 9.29289C12.3166 9.68342 11.6834 9.68342 11.2929 9.29289L8.70711 6.70711C8.31658 6.31658 8.31658 5.68342 8.70711 5.29289L11.2929 2.70711Z"--%>
<%--																	fill="currentColor"--%>
<%--															/>--%>
<%--                                                            <path--%>
<%--																	d="M11.2929 14.7071C11.6834 14.3166 12.3166 14.3166 12.7071 14.7071L15.2929 17.2929C15.6834 17.6834 15.6834 18.3166 15.2929 18.7071L12.7071 21.2929C12.3166 21.6834 11.6834 21.6834 11.2929 21.2929L8.70711 18.7071C8.31658 18.3166 8.31658 17.6834 8.70711 17.2929L11.2929 14.7071Z"--%>
<%--																	fill="currentColor"--%>
<%--															/>--%>
<%--                                                            <path--%>
<%--																	opacity="0.3"--%>
<%--																	d="M5.29289 8.70711C5.68342 8.31658 6.31658 8.31658 6.70711 8.70711L9.29289 11.2929C9.68342 11.6834 9.68342 12.3166 9.29289 12.7071L6.70711 15.2929C6.31658 15.6834 5.68342 15.6834 5.29289 15.2929L2.70711 12.7071C2.31658 12.3166 2.31658 11.6834 2.70711 11.2929L5.29289 8.70711Z"--%>
<%--																	fill="currentColor"--%>
<%--															/>--%>
<%--                                                            <path--%>
<%--																	opacity="0.3"--%>
<%--																	d="M17.2929 8.70711C17.6834 8.31658 18.3166 8.31658 18.7071 8.70711L21.2929 11.2929C21.6834 11.6834 21.6834 12.3166 21.2929 12.7071L18.7071 15.2929C18.3166 15.6834 17.6834 15.6834 17.2929 15.2929L14.7071 12.7071C14.3166 12.3166 14.3166 11.6834 14.7071 11.2929L17.2929 8.70711Z"--%>
<%--																	fill="currentColor"--%>
<%--															/>--%>
<%--                                                        </svg>--%>
<%--                                                    </span>--%>
<%--													<!--end::Svg Icon-->--%>
<%--                                                </span>--%>
<%--                                                <span class="menu-title">Order</span>--%>
<%--                                                <span class="menu-arrow"></span>--%>
<%--                                            </span>--%>
<%--				<!--end:Menu link-->--%>
<%--				<!--begin:Menu sub-->--%>
<%--				<div class="menu-sub menu-sub-accordion">--%>
<%--					<!--begin:Menu item-->--%>
<%--					<div class="menu-item active">--%>
<%--						<!--begin:Menu link-->--%>
<%--						<a class="menu-link"--%>
<%--						   href="${pageContext.request.contextPath}/ordercf">--%>
<%--                                                        <span class="menu-bullet">--%>
<%--                                                            <span class="bullet bullet-dot"></span>--%>
<%--                                                        </span>--%>
<%--							<span class="menu-title">Danh sách Order</span>--%>
<%--						</a>--%>
<%--						<!--end:Menu link-->--%>
<%--					</div>--%>
<%--					<div class="menu-item">--%>
<%--						<!--begin:Menu link-->--%>
<%--						<a class="menu-link"--%>
<%--						   href="../../demo14/dist/account/overview.html">--%>
<%--                                                        <span class="menu-bullet">--%>
<%--                                                            <span class="bullet bullet-dot"></span>--%>
<%--                                                        </span>--%>
<%--							<span class="menu-title">Thêm Order</span>--%>
<%--						</a>--%>
<%--						<!--end:Menu link-->--%>
<%--					</div>--%>
<%--					<!--end:Menu item-->--%>
<%--				</div>--%>
<%--				<!--end:Menu sub-->--%>
<%--			</div>--%>
			
			<div data-kt-menu-trigger="click"
				 class="menu-item <c:if test="${selectSubmenu eq 'account'}">show</c:if> menu-accordion">
				<!--begin:Menu link-->
				<span class="menu-link">
                                                <span class="menu-icon">
                                                    <!--begin::Svg Icon | path: icons/duotune/general/gen022.svg-->
                                                    <span class="svg-icon svg-icon-2">
                                                        <svg
																width="24"
																height="24"
																viewBox="0 0 24 24"
																fill="none"
																xmlns="http://www.w3.org/2000/svg"
														>
                                                            <path
																	d="M11.2929 2.70711C11.6834 2.31658 12.3166 2.31658 12.7071 2.70711L15.2929 5.29289C15.6834 5.68342 15.6834 6.31658 15.2929 6.70711L12.7071 9.29289C12.3166 9.68342 11.6834 9.68342 11.2929 9.29289L8.70711 6.70711C8.31658 6.31658 8.31658 5.68342 8.70711 5.29289L11.2929 2.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	d="M11.2929 14.7071C11.6834 14.3166 12.3166 14.3166 12.7071 14.7071L15.2929 17.2929C15.6834 17.6834 15.6834 18.3166 15.2929 18.7071L12.7071 21.2929C12.3166 21.6834 11.6834 21.6834 11.2929 21.2929L8.70711 18.7071C8.31658 18.3166 8.31658 17.6834 8.70711 17.2929L11.2929 14.7071Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M5.29289 8.70711C5.68342 8.31658 6.31658 8.31658 6.70711 8.70711L9.29289 11.2929C9.68342 11.6834 9.68342 12.3166 9.29289 12.7071L6.70711 15.2929C6.31658 15.6834 5.68342 15.6834 5.29289 15.2929L2.70711 12.7071C2.31658 12.3166 2.31658 11.6834 2.70711 11.2929L5.29289 8.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M17.2929 8.70711C17.6834 8.31658 18.3166 8.31658 18.7071 8.70711L21.2929 11.2929C21.6834 11.6834 21.6834 12.3166 21.2929 12.7071L18.7071 15.2929C18.3166 15.6834 17.6834 15.6834 17.2929 15.2929L14.7071 12.7071C14.3166 12.3166 14.3166 11.6834 14.7071 11.2929L17.2929 8.70711Z"
																	fill="currentColor"
															/>
                                                        </svg>
                                                    </span>
													<!--end::Svg Icon-->
                                                </span>
                                                <span class="menu-title">Tài khoản</span>
                                                <span class="menu-arrow"></span>
                                            </span>
				<!--end:Menu link-->
				<!--begin:Menu sub-->
				<div class="menu-sub menu-sub-accordion">
					<!--begin:Menu item-->
					<div class="menu-item active">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/info">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Thông tin cá nhân</span>
						</a>
						<!--end:Menu link-->
					</div>
					<div class="menu-item">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/account/change-password">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Đổi mật khẩu</span>
						</a>
						<!--end:Menu link-->
					</div>
					<!--end:Menu item-->
				</div>
				<!--end:Menu sub-->
			</div>
			<!--end:Menu item-->
			
			<div data-kt-menu-trigger="click"
				 class="menu-item <c:if test="${selectSubmenu eq 'shift'}">show</c:if> menu-accordion">
				<!--begin:Menu link-->
				<span class="menu-link">
                                                <span class="menu-icon">
                                                    <!--begin::Svg Icon | path: icons/duotune/general/gen022.svg-->
                                                    <span class="svg-icon svg-icon-2">
                                                        <svg
																width="24"
																height="24"
																viewBox="0 0 24 24"
																fill="none"
																xmlns="http://www.w3.org/2000/svg"
														>
                                                            <path
																	d="M11.2929 2.70711C11.6834 2.31658 12.3166 2.31658 12.7071 2.70711L15.2929 5.29289C15.6834 5.68342 15.6834 6.31658 15.2929 6.70711L12.7071 9.29289C12.3166 9.68342 11.6834 9.68342 11.2929 9.29289L8.70711 6.70711C8.31658 6.31658 8.31658 5.68342 8.70711 5.29289L11.2929 2.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	d="M11.2929 14.7071C11.6834 14.3166 12.3166 14.3166 12.7071 14.7071L15.2929 17.2929C15.6834 17.6834 15.6834 18.3166 15.2929 18.7071L12.7071 21.2929C12.3166 21.6834 11.6834 21.6834 11.2929 21.2929L8.70711 18.7071C8.31658 18.3166 8.31658 17.6834 8.70711 17.2929L11.2929 14.7071Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M5.29289 8.70711C5.68342 8.31658 6.31658 8.31658 6.70711 8.70711L9.29289 11.2929C9.68342 11.6834 9.68342 12.3166 9.29289 12.7071L6.70711 15.2929C6.31658 15.6834 5.68342 15.6834 5.29289 15.2929L2.70711 12.7071C2.31658 12.3166 2.31658 11.6834 2.70711 11.2929L5.29289 8.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M17.2929 8.70711C17.6834 8.31658 18.3166 8.31658 18.7071 8.70711L21.2929 11.2929C21.6834 11.6834 21.6834 12.3166 21.2929 12.7071L18.7071 15.2929C18.3166 15.6834 17.6834 15.6834 17.2929 15.2929L14.7071 12.7071C14.3166 12.3166 14.3166 11.6834 14.7071 11.2929L17.2929 8.70711Z"
																	fill="currentColor"
															/>
                                                        </svg>
                                                    </span>
													<!--end::Svg Icon-->
                                                </span>
                                                <span class="menu-title">Quản lý ca làm</span>
                                                <span class="menu-arrow"></span>
                                            </span>
				<!--end:Menu link-->
				<!--begin:Menu sub-->
				<div class="menu-sub menu-sub-accordion">
					<!--begin:Menu item-->
					<div class="menu-item active">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/shift/request">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Đăng ký ca làm việc</span>
						</a>
						<!--end:Menu link-->
					</div>
					<div class="menu-item">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/shift/all">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Lịch làm việc</span>
						</a>
						<!--end:Menu link-->
					</div>
					<!--end:Menu item-->
				</div>
				<!--end:Menu sub-->
			</div>
			<!--end:Menu item-->
			
			<div data-kt-menu-trigger="click"
				 class="menu-item <c:if test="${selectSubmenu eq 'shiftRotate'}">show</c:if> menu-accordion">
				<!--begin:Menu link-->
				<span class="menu-link">
                                                <span class="menu-icon">
                                                    <!--begin::Svg Icon | path: icons/duotune/general/gen022.svg-->
                                                    <span class="svg-icon svg-icon-2">
                                                        <svg
																width="24"
																height="24"
																viewBox="0 0 24 24"
																fill="none"
																xmlns="http://www.w3.org/2000/svg"
														>
                                                            <path
																	d="M11.2929 2.70711C11.6834 2.31658 12.3166 2.31658 12.7071 2.70711L15.2929 5.29289C15.6834 5.68342 15.6834 6.31658 15.2929 6.70711L12.7071 9.29289C12.3166 9.68342 11.6834 9.68342 11.2929 9.29289L8.70711 6.70711C8.31658 6.31658 8.31658 5.68342 8.70711 5.29289L11.2929 2.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	d="M11.2929 14.7071C11.6834 14.3166 12.3166 14.3166 12.7071 14.7071L15.2929 17.2929C15.6834 17.6834 15.6834 18.3166 15.2929 18.7071L12.7071 21.2929C12.3166 21.6834 11.6834 21.6834 11.2929 21.2929L8.70711 18.7071C8.31658 18.3166 8.31658 17.6834 8.70711 17.2929L11.2929 14.7071Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M5.29289 8.70711C5.68342 8.31658 6.31658 8.31658 6.70711 8.70711L9.29289 11.2929C9.68342 11.6834 9.68342 12.3166 9.29289 12.7071L6.70711 15.2929C6.31658 15.6834 5.68342 15.6834 5.29289 15.2929L2.70711 12.7071C2.31658 12.3166 2.31658 11.6834 2.70711 11.2929L5.29289 8.70711Z"
																	fill="currentColor"
															/>
                                                            <path
																	opacity="0.3"
																	d="M17.2929 8.70711C17.6834 8.31658 18.3166 8.31658 18.7071 8.70711L21.2929 11.2929C21.6834 11.6834 21.6834 12.3166 21.2929 12.7071L18.7071 15.2929C18.3166 15.6834 17.6834 15.6834 17.2929 15.2929L14.7071 12.7071C14.3166 12.3166 14.3166 11.6834 14.7071 11.2929L17.2929 8.70711Z"
																	fill="currentColor"
															/>
                                                        </svg>
                                                    </span>
													<!--end::Svg Icon-->
                                                </span>
                                                <span class="menu-title">Yêu cầu xoay ca</span>
                                                <span class="menu-arrow"></span>
                                            </span>
				<!--end:Menu link-->
				<!--begin:Menu sub-->
				<div class="menu-sub menu-sub-accordion">
					<!--begin:Menu item-->
					<div class="menu-item active">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/shiftRotate/my">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Của bản thân</span>
						</a>
						<!--end:Menu link-->
					</div>
					<div class="menu-item">
						<!--begin:Menu link-->
						<a class="menu-link"
						   href="${pageContext.request.contextPath}/staff/shiftRotate/request">
                                                        <span class="menu-bullet">
                                                            <span class="bullet bullet-dot"></span>
                                                        </span>
							<span class="menu-title">Được đề nghị</span>
						</a>
						<!--end:Menu link-->
					</div>
					<!--end:Menu item-->
				</div>
				<!--end:Menu sub-->
			</div>
			<!--end:Menu item-->
		
		</div>
		<!--end::Menu-->
	</div>
</div>