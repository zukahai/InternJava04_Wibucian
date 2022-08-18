<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>


<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
    <!--begin::Card header-->
    <div class="card-header cursor-pointer">
        <!--begin::Card title-->
        <div class="card-title m-0">
            <h3 class="fw-bold m-0"><a href="${pageContext.request.contextPath}/admin/importgoods">Danh sách nhập
                kho</a></h3>
        </div>
        <!--end::Card title-->
        <!--begin::Action-->

        <!--end::Action-->
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9">
        <!--begin::Wrapper-->
        <div class="card p-9">
            <div hidden class="mb-10">
                <label for="id-import-good" class="required form-label">Id nhập kho</label>
                <input id="id-import-good" type="text" value="${importgood.id}" readonly
                       class="form-control id-invoice form-control-solid"
                       placeholder="Example input"/>
            </div>
            <div class="mb-10">
                <label for="name-employee-invoice" class="required form-label">Nhân viên</label>
                <input data-value="${importgood.employee.name}" id="name-employee-invoice" type="text"
                       value="${importgood.employee.name}" readonly class="form-control form-control-solid"
                       placeholder="Example input"/>
            </div>

            <div class="mb-10">
                <label for="name-employee-invoice" class="required form-label">Ngày nhập kho</label>
                <input data-value="${importgood.timeImport}" id="date-bill-invoice" type="text"
                       value="${importgood.timeImport}" readonly class="form-control form-control-solid"
                       placeholder="Example input"/>
            </div>
        </div>
        <div class="card mb-5 mb-xl-10">
            <div class="card-header cursor-pointer">
                <!--begin::Card title-->
                <div class="card-title m-0">
                    <h3 class="fw-bold m-0">Danh sách chi tiết hoá đơn</h3>
                </div>
                <!--end::Card title-->
                <!--begin::Action-->
                <span class="btn btn-primary align-self-center add-detail"><i
                        class="fa fa-plus"></i>Thêm chi tiết</span>
                <!--end::Action-->
            </div>
            <!--begin::Card header-->
            <!--begin::Card body-->
            <div class="card-body">
                <!--begin::Wrapper-->
                <div class="mb-10">
                    <table class="table table-responsive table-row-bordered" id="tabel-invoice">
                        <!--begin::Table head-->
                        <thead>
                        <!--begin::Table row-->
                        <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                            <th scope="col" class="min-w-100px"><h5>Tên nguyên liệu</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Số lượng</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Đơn vị</h5></th>
                            <th scope="col" class="min-w-100px"><h4>Hành động</h4></th>
                        </tr>
                        <!--end::Table row-->
                        </thead>

                        <tbody class="text-gray-600 fw-semibold">
                        <c:forEach items="${importgood.detailImportGoods}" var="item">

                            <tr class="text-start id-detail-ingredient-${item.id}">
                                <td><p class="name-ingredient-tabel">${item.ingredient.ingredientName}</p></td>
                                <td><p class="quantity-table">${item.quantity}</p></td>
                                <td><p class="unit-table">${item.ingredient.unit}</p></td>
                                <td>
                                    <span data-action="${item.id}"
                                          class="btn btn-icon btn-primary btn-edit  btn-sm btn-icon-md btn-circle"
                                          data-toggle="tooltip" data-placement="top" title="Sửa"><i
                                            class="fa fa-edit"></i>
                                    </span>
                                    <span data-action="${item.id}"
                                          class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                          data-toggle="tooltip" data-placement="top" title="Xóa"><i
                                            class="fa fa-trash"></i>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>


                    </table>

                </div>
                <!--end::Wrapper-->
            </div>
        </div>
    </div>

    <div id="modal-add-ingredient" role="dialog" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title-add-ingredient"></h3>
                    <!--begin::Close-->
                    <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                         aria-label="Close">
                        <span class="svg-icon svg-icon-1"></span>
                    </div>
                    <!--end::Close-->
                </div>
                <div class="modal-body">
                    <div  class="mb-10">
                        <label class="required form-label">Id Hoá Đơn</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid id-import-good-modal"
                               placeholder="Example input"/>
                    </div>
                    <div class="mb-10">
                        <div class="mb-10">
                            <label class="required form-label">Chọn nguyên liệu</label>
                            <select class="form-select select-2-ingredient form-select-solid" data-control="select2"
                                    data-placeholder="Chọn nguyên liệu">
                                <c:forEach items="${ingredients}" var="item">
                                    <option value="${item.id}">${item.ingredientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="input-group mb-5">
                        <span class="input-group-text me-2" id="basic-addon3">Số lượng</span>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count "
                              id="down-count">-</span>
                        <input type="number" value="1" class="form-control text-center text-count" id="count"/>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark  up-count btn-sm btn-icon-md btn-circle "
                              id="up-count">+</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary save-btn-add-ingredient">Lưu lại</button>
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <div id="modal-edit-ingredient" role="dialog" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title-edit-ingredient"></h3>
                    <!--begin::Close-->
                    <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                         aria-label="Close">
                        <span class="svg-icon svg-icon-1"></span>
                    </div>
                    <!--end::Close-->
                </div>
                <div  class="modal-body">
                    <div  class="mb-10">
                        <label class="required form-label">Id chi tiết nhập hàng</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid id-detail-import-good-modal"
                               placeholder="Example input"/>
                    </div>
                    <div  class="mb-10">
                        <label class="required form-label">Tên nguyên liệu</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid name-ingredient-modal"
                               placeholder="Example input"/>
                    </div>

                    <div class="input-group mb-5">
                        <span class="input-group-text me-2" >Số lượng</span>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count "
                        >-</span>
                        <input type="number" value="1" step="1.0" class="form-control text-center text-count-edit" />
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark  up-count btn-sm btn-icon-md btn-circle "
                        >+</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary save-btn-edit-ingredient">Lưu lại</button>
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script>
    //handel delete-btn
    $(document).on("click", ".delete-btn", function (e) {
        var id = $(this).attr("data-action");
        var that = $(this);
        var row = that.closest("tr");
        swal.fire({
            title: "Bạn có chắc chắn muốn xóa?",
            text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Hủy bỏ",
        }).then((result) => {
            if (result.value) {
                $.ajax({
                    url: "/admin/detailImportGoods/" + id,
                    type: "Delete",
                    success: function (result) {
                        if (result.check == true) {
                            toastr.success("Xóa thành công");
                            row.remove();
                        } else {
                            toastr.error("Xóa thất bại");
                        }
                    }
                });
            }
        });
    });
    $(document).on("click", ".btn-edit", function () {
        $('.modal-title-edit-ingredient').text("Sửa chi tiết hoá đơn " + $(this).attr("data-action"));
        $('.id-detail-import-good-modal').val($(this).attr("data-action"));
        $('.id-detail-import-good-modal').attr("data-action", $(this).attr("data-action"));
        $('.name-ingredient-modal').val($(this).closest("tr").find(".name-ingredient-tabel").text());
        $('.text-count-edit').val($(this).closest("tr").find(".quantity-table").text());
        $('#modal-edit-ingredient').modal('show');
    });
    //hadle up-count
    $(document).on("click", ".up-count", function () {
        var count = $('.text-count').val();
        count++;
        $('.text-count').val(count);
        $('.text-count-edit').val(count);
    });
    //hadle down-count
    $(document).on("click", ".down-count", function () {
        var count = $('.text-count').val();
        if (count > 1) {
            count--;
            $('.text-count').val(count);
            $('.text-count-edit').val(count);
        }
    });
    //handel save-btn
    $(document).on("click", ".save-btn-edit-ingredient", function () {
        var id = $('.id-detail-import-good-modal').attr("data-action");
        var quantity = $('.text-count-edit').val();
        let data = {
            idDetailImportGoods: id,
            quantity: quantity
        };
        $.ajax({
            url: "/admin/detailImportGoods/" + id,
            contentType: "application/json",
            type: "PUT",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check == true) {
                    toastr.success("Cập nhật thành công");
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);

                } else {
                    toastr.error("Cập nhật thất bại");
                }
            }
        });
    });
    let validatePriceToVND = (price) => {
        return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(price)
    }
    $('.select-2-ingredient').select2();
    //handel add-detail
    $(document).on("click", ".add-detail", function () {
        $('.modal-title-add-ingredient').text("Thêm chi tiết hoá đơn");
        $('.id-import-good-modal').val($('#id-import-good').val());
        $('.text-count').val(1);
        $('#modal-add-ingredient').modal('show');
    });
    //handel save-btn-add-detail
    $(document).on("click", ".save-btn-add-ingredient", function () {
        var id = $('.id-import-good-modal').val();
        console.log(id);
        var quantity = $('.text-count').val();
        var idIngredient = $('.select-2-ingredient').val();
        let data = {
            idImportGoods: id,
            idIngredient: idIngredient,
            quantity: quantity
        }
        console.log(JSON.stringify(data));
        $.ajax({
            url: "/admin/detailImportGoods",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check == true) {
                    toastr.success("Cập nhật thành công");
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                }
            }
        })
    });
    //handel save-name-invoice
    $(document).on("click", ".save-name-invoice", function () {
        var id = $('.id-invoice').val();
        var name = $('.name-customer-invoice').val();
        let data = {
            idInvoice: id,
            customerName: name
        }
        $.ajax({
            url: "/invoice/" + id,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check == true) {
                    toastr.success("Cập nhật thành công");
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                }
            }
        })
    });

    ///handel invoice-save
    $(document).on("click", ".invoice-save", function () {
        var id = $('.id-invoice').val();
        var name = $('.name-customer-invoice').val();
        let data = {
            idInvoice: id,
            status: 2
        }
        $.ajax({
            url: "/invoice/" + id,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check == true) {
                    toastr.success("Cập nhật thành công");
                    $('.invoice-save').hide();
                    let id = $('#id-invoice').val();
                    let name = $('#name-customer-invoice').val();
                    let total_price = $('.total-money-price-end').text().trim();
                    console.log(id, name, total_price)
                    $('#modal-show-qr-code').find('.modal-title').text("Mã QR đơn hàng " + id);
                    $('#modal-show-qr-code').find('.customer-detail-invoice').val(name);
                    $('#modal-show-qr-code').find('.total-price-invoice').val(total_price);
                    $('#modal-show-qr-code').modal('show');
                    $.ajax({
                        url: "/qrcode/",
                        contentType: "application/json",
                        type: "POST",
                        data: JSON.stringify({
                            content: "Hải Okoko",
                            width: 200,
                            height: 200
                        }),
                        success: function (resutl) {
                            $(".qr-code-modal").attr("src", `data:image/png;base64,` + resutl);
                        }
                    });
                }
            }
        })
    });
    //handel .invoice-qr-code btn
    $(document).on('click', '.invoice-qr-code', function () {
        let id = $('#id-invoice').val();
        let name = $('#name-customer-invoice').val();
        let total_price = $('.total-money-price-end').text().trim();
        console.log(id, name, total_price)
        $('#modal-show-qr-code').find('.modal-title').text("Mã QR đơn hàng " + id);
        $('#modal-show-qr-code').find('.customer-detail-invoice').val(name);
        $('#modal-show-qr-code').find('.total-price-invoice').val(total_price);
        $('#modal-show-qr-code').modal('show');
        $.ajax({
            url: "/qrcode/",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify({
                content: "Tên khách hàng: " + name + '\n\n' + "Tổng số tiền: " + total_price,
                width: 200,
                height: 200
            }),
            success: function (resutl) {
                $(".qr-code-modal").attr("src", `data:image/png;base64,` + resutl);
            }
        });
    });
    $(document).on("click", '.invoice-print', function () {
        //click print-datatable
        $('.print-datatable').click();
    })

</script>