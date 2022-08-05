<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar1.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<div class="content flex-column-fluid" id="kt_content">

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <!--begin::Card header-->
        <div class="card-header cursor-pointer">
            <!--begin::Card title-->
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Profile Details</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="../../demo14/dist/account/settings.html"
                    class="btn btn-primary align-self-center"
            >Edit Profile</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <!--begin::Wrapper-->

            <div class="d-flex flex-stack mb-5">
                <table class="table align-middle table-row-dashed fs-6 gy-5" id="kt_table_users">
                    <!--begin::Table head-->
                    <thead>
                    <!--begin::Table row-->
                    <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                        <th class="w-10px pe-2">
                            <div class="form-check form-check-sm form-check-custom form-check-solid me-3">
                                <input class="form-check-input" type="checkbox" data-kt-check="true"
                                       data-kt-check-target="#kt_table_users .form-check-input" value="1"/>
                            </div>
                        </th>
                        <th class="min-w-125px">User</th>
                        <th class="min-w-125px">Role</th>
                        <th class="min-w-125px">Last login</th>
                        <th class="min-w-125px">Two-step</th>
                        <th class="min-w-125px">Joined Date</th>
                        <th class="text-end min-w-100px">Actions</th>
                    </tr>
                    <!--end::Table row-->
                    </thead>
                    <!--end::Table head-->
                    <!--begin::Table body-->
                    <tbody class="text-gray-600 fw-semibold">
                    <!--begin::Table row-->
                    <tr>
                        <!--begin::Checkbox-->
                        <td>
                            <div class="form-check form-check-sm form-check-custom form-check-solid">
                                <input class="form-check-input" type="checkbox" value="1"/>
                            </div>
                        </td>
                        <!--end::Checkbox-->
                        <!--begin::User=-->
                        <td class="d-flex align-items-center">
                            <!--begin:: Avatar -->
                            <%--                            <div class="symbol symbol-circle symbol-50px overflow-hidden me-3">--%>
                            <%--                                <a href="../../demo14/dist/apps/user-management/users/view.html">--%>
                            <%--                                    <div class="symbol-label">--%>
                            <%--&lt;%&ndash;                                        <img src="assets/media/avatars/300-6.jpg" alt="Emma Smith" class="w-100" />&ndash;%&gt;--%>
                            <%--                                    </div>--%>
                            <%--                                </a>--%>
                            <%--                            </div>--%>
                            <!--end::Avatar-->
                            <!--begin::User details-->
                            <div class="d-flex flex-column">
                                <a href="../../demo14/dist/apps/user-management/users/view.html"
                                   class="text-gray-800 text-hover-primary mb-1">Emma Smith</a>
                                <span>smith@kpmg.com</span>
                            </div>
                            <!--begin::User details-->
                        </td>
                        <!--end::User=-->
                        <!--begin::Role=-->
                        <td>Administrator</td>
                        <!--end::Role=-->
                        <!--begin::Last login=-->
                        <td>
                            <div class="badge badge-light fw-bold">Yesterday</div>
                        </td>
                        <!--end::Last login=-->
                        <!--begin::Two step=-->
                        <td></td>
                        <!--end::Two step=-->
                        <!--begin::Joined-->
                        <td>
                            <div class="badge badge-light-success badge-lg">Primary</div>
                        </td>
                        <!--begin::Joined-->
                        <!--begin::Action=-->
                        <td class="text-end">
                            <a href="#" class="btn btn-light btn-active-light-primary btn-sm"
                               data-kt-menu-trigger="click" data-kt-menu-placement="bottom-end">Actions
                                <!--begin::Svg Icon | path: icons/duotune/arrows/arr072.svg-->
                                <span class="svg-icon svg-icon-5 m-0">
															<svg width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                                 xmlns="http://www.w3.org/2000/svg">
																<path d="M11.4343 12.7344L7.25 8.55005C6.83579 8.13583 6.16421 8.13584 5.75 8.55005C5.33579 8.96426 5.33579 9.63583 5.75 10.05L11.2929 15.5929C11.6834 15.9835 12.3166 15.9835 12.7071 15.5929L18.25 10.05C18.6642 9.63584 18.6642 8.96426 18.25 8.55005C17.8358 8.13584 17.1642 8.13584 16.75 8.55005L12.5657 12.7344C12.2533 13.0468 11.7467 13.0468 11.4343 12.7344Z"
                                                                      fill="currentColor"/>
															</svg>
														</span>
                                <!--end::Svg Icon--></a>
                            <!--begin::Menu-->
                            <div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-600 menu-state-bg-light-primary fw-semibold fs-7 w-125px py-4"
                                 data-kt-menu="true">
                                <!--begin::Menu item-->
                                <div class="menu-item px-3">
                                    <a href="../../demo14/dist/apps/user-management/users/view.html"
                                       class="menu-link px-3 btn btn-primary btn-sm text-white text-center">Xem</a>
                                </div>
                                <div class="menu-item px-3">
                                    <a href="../../demo14/dist/apps/user-management/users/view.html"
                                       class="menu-link px-3 btn btn-success btn-sm text-white text-center">Sửa</a>
                                </div>
                                <!--end::Menu item-->
                                <!--begin::Menu item-->
                                <div class="menu-item px-3">
                                    <a href="#" class="menu-link px-3 btn btn-danger btn-sm text-white text-center"
                                       data-kt-users-table-filter="delete_row ">Xoá</a>
                                </div>
                                <!--end::Menu item-->
                            </div>
                            <!--end::Menu-->
                        </td>
                        <!--end::Action=-->
                    </tr>

                    <!--end::Table row-->
                    </tbody>
                    <!--end::Table body-->
                </table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script !src="">
    $("#kt_datatable_column_rendering").DataTable({
        select: true,
        responsive: true,
        searching: true,
        search: {},
        paging: true,
        ordering: true,
        info: true,


    });
</script>
