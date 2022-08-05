<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar1.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <!--begin::Card header-->
        <div class="card-header cursor-pointer">
            <!--begin::Card title-->
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Profile Details</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a href="../../demo14/dist/account/settings.html" class="btn btn-primary align-self-center">Edit Profile</a>
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <!--begin::Wrapper-->
            <form class="form-submit" >
                <div class="mb-10">
                    <label for="id-table" class="required form-label">Chọn bàn</label>
                    <select id="id-table" class="form-select form-select-solid" data-control="select2"
                            data-placeholder="Chọn Bàn">
                        <option></option>
                        <option value="1">Bàn 1</option>
                        <option value="2">Bàn 2</option>
                        <option value="3">Bàn 3</option>
                        <option value="4">Bàn 4</option>
                        <option value="5">Bàn 5</option>
                        <option value="6">Bàn 6</option>
                        <option value="7">Bàn 7</option>
                        <option value="8">Bàn 8</option>

                    </select>
                </div>
                <div class="row  d-flex  justify-content-between">
                    <div class="mb-5 col">
                        <select id="id-table-product" class="form-select form-select-solid" data-control="select2"
                                data-placeholder="Chọn sản phẩm">
                            <option></option>
                            <option value="1">Sản phẩm 1</option>
                            <option value="2">Sản phẩm 2</option>
                            <option value="3">Sản phẩm 3</option>
                            <option value="4">Sản phẩm 4</option>
                            <option value="5">Sản phẩm 5</option>
                            <option value="6">Sản phẩm 6</option>
                            <option value="7">Sản phẩm 7</option>
                            <option value="8">Sản phẩm 8</option>
                        </select>

                    </div>
                    <div class="col ">
                        <div class="input-group mb-5">
                            <span class="input-group-text me-2">Giá</span>
                            <input  type="text" value="35 000" class="form-control text-center price-prod" id="price-prod" />
                        </div>
                    </div>
                    <div class="col col-3">
                        <div class="input-group mb-5">
                            <span class="input-group-text me-2" id="basic-addon3">Số lượng</span>
                            <span class="input-group-text btn  btn-dark text-center  down-count " id="down-count">-</span>
                            <input type="text" value="1" class="form-control text-center text-count" id="count" />
                            <span class="input-group-text btn  btn-dark text-center  up-count " id="up-count">+</span>
                        </div>
                    </div>
                    <div class="col ">
                        <div readonly="true" class="input-group mb-5">
                            <span class="input-group-text" >Tổng tiền</span>
                            <input type="text" class="form-control total-price" id="total-price"  aria-describedby="basic-addon3"/>
                        </div>
                    </div>
                    <div class="col">
                        <button class="btn btn-primary align-self-center">Thêm sản phẩm</button>
                    </div>
                </div>
                <hr>
                <h3>Danh sách sản phẩm order</h3>
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
                            <th class="min-w-125px">Tên sản phẩm</th>
                            <th class="min-w-125px">Số lượng</th>
                            <th class="min-w-125px">Giá</th>
                            <th class="min-w-100px">Tổng tiền</th>
                            <th class="text-end min-w-100px">Hành động</th>
                        </tr>
                        <!--end::Table row-->
                        </thead>
                        <!--end::Table head-->
                        <!--begin::Table body-->
                        <tbody class="text-gray-600 fw-semibold">
                        <!--begin::Table row-->
                        <tr>

                            <td>
                                <div class="form-check form-check-sm form-check-custom form-check-solid">
                                    <input class="form-check-input" type="checkbox" value="1"/>
                                </div>
                            </td>
                            <td>Trà sữa trân châu</td>
                            <td>12</td>
                            <td>35 000</td>
                            <td class="total-price">12 * 35000</td>
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
                                        <a href="#"
                                           class="menu-link px-3 btn btn-danger btn-sm text-white text-center delete-btn"
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
            </form>
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
    //handle click on .delete-btn button
    $(".delete-btn").click(function () {
        var id = $(this).data("id");
        var btn = $(this);
        swal.fire({
            title: 'Bạn có chắc chắn muốn xoá?',
            text: "Bạn sẽ không thể khôi phục lại dữ liệu này!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xoá',
            cancelButtonText: 'Hủy',
            reverseButtons: true
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/delete/" + id,
                    type: "DELETE",
                    data: {
                        id: id
                    },
                    success: function (response) {
                        if (response.check === true) {
                            swal.fire({
                                title: 'Xoá thành công!',
                                text: 'Dữ liệu đã được xoá.',
                                icon: 'success',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            btn.closest("tr").remove();


                        } else {
                            swal.fire({
                                title: 'Xoá thất bại!',
                                text: 'Dữ liệu đã được xoá.',
                                icon: 'error',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    }
                });
            } else if (result.dismiss === 'cancel') {
                swal.fire({
                    title: 'Hủy bỏ',
                    text: 'Dữ liệu được bảo toàn.',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    });

    //handle click on up-count and down-count button
    $(".up-count").click(function () {
        var count_text  = $(this).siblings(".text-count");
        var total_price = $("#total-price");
        var price_product = $("#price-prod");
        var count = parseInt(count_text.val() );
        count_text.val(count+1);
        var price = validatePrice(price_product.val());
        console.log(price);
        total_price.val(price*count);
    });

    $(".down-count").click(function () {
        var count_text  = $(this).siblings(".text-count");
        var count = parseInt(count_text.val());
        if (count > 0) {
            count_text.val(count - 1);
        }
    });
    function validatePrice(price){
        //delete all character except number script
        var price_new = price.replace(/[^0-9]/g, '');
        return price_new;
    }




</script>
