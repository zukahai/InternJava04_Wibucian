<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
    <!--begin::Card header-->
    <div class="card-header cursor-pointer">
        <!--begin::Card title-->
        <div class="card-title m-0">
            <h3 class="fw-bold m-0">Order</h3>
        </div>
        <!--end::Card title-->
        <!--begin::Action-->
        <!--end::Action-->
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9">
        <!--begin::Wrapper-->
        <form class="form-submit">
            <div class="mb-10">
                <label for="id-table-select" class="required form-label">Chọn bàn</label>
                <select id="id-table-select" class="form-select form-select-solid" data-control="select2"
                        data-placeholder="Chọn Bàn">
                    <option value="all">Tất cả các bàn</option>
                    <c:forEach items="${groupTables}" var="item">
                        <option value="${item.id}">${item.groupName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="add-product-layout">
                <div class="mb-10">
                    <label for="id-table-select" class="required form-label">Chọn Sản Phẩm</label>
                    <select id="id-select-product" class="form-select form-select-solid" data-control="select2"
                            data-placeholder="Chọn sản phẩm">
                        <c:forEach items="${products}" var="product">
                            <option data-name="${product.productName}" data-price="${product.price}"
                                    value="${product.id}">${product.productName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row  d-flex  justify-content-between ">

                    <div class="col ">
                        <div class="input-group mb-5">
                            <span class="input-group-text me-2">Giá</span>
                            <input readonly type="text" value="<fmt:formatNumber pattern="#,###" value="${products[0].price}"/>"
                                   class="form-control text-center price-product"
                                   id="price-prod"/>
                        </div>
                    </div>
                    <div class="col col-3 count-div">
                        <div class="input-group mb-5">
                            <span class="input-group-text me-2" id="basic-addon3">Số lượng</span>
                            <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count "
                            >-</span>
                            <input type="text" value="1" class="form-control text-center text-count"/>
                            <span class="input-group-text btn  abtn-light-secondary btn-outline text-center text-dark  up-count btn-sm btn-icon-md btn-circle "
                            >+</span>
                        </div>
                    </div>
                    <div class="col ">
                        <div readonly="true" class="input-group mb-5">
                            <span class="input-group-text">Tổng tiền</span>
                            <input readonly value="<fmt:formatNumber pattern="#,###" value="${products[0].price}"/>" type="text"
                                   class="form-control total-price" id="total-price"
                                   aria-describedby="basic-addon3"/>
                        </div>
                    </div>
                    <div class="mb-5 col">
                        <select id="id-select-status" class="form-select form-select-solid" data-control="select2"
                                data-placeholder="Chọn Trạng Thái">
                            <option value="1">Đang Chờ</option>
                        </select>
                    </div>
                    <div class="col">
                        <spam class="btn btn-primary align-self-center add-product-order">Thêm sản phẩm</spam>
                    </div>
                </div>
            </div>

            <hr>
            <h3>Danh sách sản phẩm order</h3>

            <div class="d-flex flex-stack ">

                <table class="table table-row-dashed table-row-gray-300 gy-7" id="table-order">
                    <!--begin::Table head-->
                    <thead>
                    <!--begin::Table row-->
                    <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0 ">
                        <th class=" text-dark display-2">Tên Bàn</th>
                        <th class="min-w-125px text-dark display-2">Tên sản phẩm</th>
                        <th class="min-w-100px text-dark display-2">Số lượng</th>
                        <th class="min-w-100px text-dark text-center display-2">Giá</th>
                        <th class="min-w-100px text-dark display-2 ">Thời gian</th>
                        <th class="min-w-125px text-dark display-2">Trạng Thái</th>
                        <th class="min-w-100px text-dark display-2">Hành động</th>
                    </tr>
                    <!--end::Table row-->
                    </thead>
                    <!--end::Table head-->
                    <!--begin::Table body-->
                    <tbody class="text-gray-600 fw-semibold">
                    <!--begin::Table row-->

                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
<div id="modal-edit-order" role="dialog" class="modal fade" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title-edit-order"></h3>
                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                     aria-label="Close">
                    <span class="svg-icon svg-icon-1"></span>
                </div>
                <!--end::Close-->
            </div>
            <div class="modal-body">
                <div class="mb-10">
                    <label class="required form-label">Id Order</label>
                    <input data-action="" readonly type="text"
                           class="form-control form-control-solid id-order-modal"
                           placeholder="Example input"/>
                </div>
                <div class="mb-10">
                    <label class="required form-label">Tên Sản phẩm</label>
                    <input data-action="" readonly type="text"
                           class="form-control form-control-solid name-product-modal"
                           placeholder="Example input"/>
                </div>
                <div class="mb-10">
                    <label class="required form-label">Trạng thái</label>
                    <input data-action="" readonly type="text"
                           class="form-control form-control-solid status-order-modal"
                           placeholder="Example input"/>
                </div>
                <div class="input-group mb-5">
                    <span class="input-group-text me-2">Số lượng</span>
                    <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count-edit ">-</span>
                    <input type="number" value="1" step="1.0" class="form-control text-center text-count-edit"/>
                    <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark  up-count-edit btn-sm btn-icon-md btn-circle ">+</span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary save-btn-edit-order">Lưu lại</button>
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script !src="">
    $(document).ready(function () {
        $("#table-order").DataTable({
            dom: 'Bfrtip',
            order: [[5, "desc"]],
        });
    });
    //handle click on down-count-edit
    $(document).on('click', '.down-count-edit', function () {
        var edit = $(this).closest('div').find('.text-count-edit');
        var count = edit.val();
        if (count > 1) {
            count--;
            edit.val(count);
        }
    });
    $(document).on('click', '.up-count-edit', function () {
        var edit = $(this).closest('div').find('.text-count-edit');
        var count = edit.val();
        count++;
        edit.val(count);
    });

    //handle click on up-count and down-count button
    $(document).on("click", ".up-count", function () {
        var count = parseInt($(this).siblings(".text-count").val());
        count++;
        $(this).siblings(".text-count").val(count);
        var price_product = $(".price-product");
        var total_price = $(".total-price");
        var total_product = $(".total-product");
        var price_product_value = validatePrice(price_product.val());
        var total_price_value = count * price_product_value;
        total_price.val(validatePriceToVND(total_price_value));
    });
    $(document).on("click", ".down-count", function () {
        var count = parseInt($(this).siblings(".text-count").val());
        if (count > 1) {
            count--;
            $(this).siblings(".text-count").val(count);
            var price_product = $(".price-product");
            var total_price = $(".total-price");
            var total_product = $(".total-product");
            var price_product_value = validatePrice(price_product.val());
            var total_price_value = count * price_product_value;
            total_price.val(validatePriceToVND(total_price_value));
        }
    });

    function validatePrice(price) {
        //delete all character except number script
        var price_new = price.replace(/[^0-9]/g, '');
        return price_new;
    }

    $(document).on("click", ".down-up-table", function () {
        var count_text = $(this).siblings(".text-count-table");
        var total_price = $(this).closest("tr").find(".total-price-table");
        var price_product = $(this).closest("tr").find(".price-product-table");
        var count = parseInt(count_text.val());
        count_text.val(count + 1);
        var price = validatePrice(price_product.text());
        total_price.text(price * (count + 1));
    });
    $(document).on("click", ".down-count-table", function () {
        var count_text = $(this).siblings(".text-count-table");
        var total_price = $(this).closest("tr").find(".total-price-table");
        var price_product = $(this).closest("tr").find(".price-product-table");
        var count = parseInt(count_text.val());
        if (count > 1) {
            count_text.val(count - 1);
            var price = validatePrice(price_product.text());
            total_price.text(price * (count - 1));
        }
    });
    //handle on type in text-count change value of total-price
    $(document).on("input", ".text-count", function () {
        var count = parseInt($(this).val());
        $(this).siblings(".text-count").val(count);
        var price_product = $(".price-product");
        var total_price = $(".total-price");
        var total_product = $(".total-product");
        var price_product_value = validatePrice(price_product.val());
        var total_price_value = count * price_product_value;
        total_price.val(total_price_value);
    });
    //handle on type in text-count-table change value of total-price
    $(document).on("input", ".text-count-table", function () {
        var count = parseInt($(this).val());
        $(this).siblings(".text-count-table").val(count);
        var price_product = $(this).closest("tr").find(".price-product-table");
        var total_price = $(this).closest("tr").find(".total-price-table");
        var price_product_value = validatePrice(price_product.text());
        var total_price_value = count * price_product_value;
        total_price.text(total_price_value);
    });
    let validateSelect = (status) => {
        if (status == 1) {
            return `<option selected value="1">Đang Chờ</option>
                   <option value="2">Đã Xong</option>
                   <option value="3">Đã Huỷ</option>`
        }
        if (status == 2) {
            return `<option selected value="2">Đã Xong</option>`
        }
        if (status == 3) {
            return `<option selected value="3">Đã Huỷ</option>`
        }
    }
    //handel add-product-order button
    $(document).on("click", ".add-product-order", function () {
        var count = $('.count-div').find(".text-count").val();
        console.log(count);
        var count = parseInt($(".text-count").val());
        var id_product = $("#id-select-product").val();
        var status = $("#id-select-status").val();
        var id_group_table = $("#id-table-select");
        var id_group_table_value = id_group_table.val();
        $.ajax({
            url: "/admin/ordercf",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify({
                idGroupTable: id_group_table_value,
                idProduct: id_product,
                quantity: count,
                status: status,
            }),
            success: function (result) {
                if (result.check === true) {
                    toastr.success("Thêm thành công");
                    update_data(id_group_table_value);
                } else {
                    console.log(result.message);
                    Swal.fire({
                        text: result.message,
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok",
                        customClass: {
                            confirmButton: "btn btn-danger"
                        }
                    });
                }
            }
        });
    });
    let updateSelectInTabel = () => {
        $(".sellect-2").select2();
    }
    let validatePriceToVND = (price) => {
        return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(price)
    }
    let getHTMLRowTabel = (data) => {
        return `<tr>
                        <td ><p class="group-table-name"-table>` + data.group_table_name + `</p></td>
                        <td style="display: none"><p class="id-group-table">` + data.id_group_table + `</p></td>
                        <td style="display: none"><p class="id-ordercf-table">` + data.id + `</p></td>
                        <td style="display: none"><p class="id-idOrdercf-table">` + data.idOrdercf + `</p></td>
                        <td style="display: none"><p class="count">` + data.count + `</p></td>
                        <td style="display: none"><p class="id-product-table">` + data.id_product + `</p></td>
                        <td><p class="name-product-table">` + data.name_product + `</p></td>
                        <td><p class="text-count-table">` + data.count + `</p></td>
                        <td class="text-center"><p class="price-product-table">` + validatePriceToVND(data.price_product) + `</p></td>
                        <td style="display: none" class="total-price-table"><p class="total-product-table">` + data.total_price + `</p></td>
                        <td class="time-order-table"><p class="total-product-table">` + data.time_order + `</p></td>
                        <td data-status = "` + data.status + `" class="status-table">
                            <div class="mb-5 col">
                                <select class="form-select form-select-solid sellect-2" data-control="select2"
                                    data-placeholder="Chọn Trạng Thái">
                                    ` + data.option_html + `
                                </select>
                            </div>
                        </td>
                        <td class="">
                            ` + checkStatus(data.status) + `
                            <span class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                  data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                        </td>
                    </tr>`
    }
    let checkStatus = (status) => {
        if (status != 2 && status != 3) {
            return `<span class="btn btn-icon btn-primary edit-btn btn-sm btn-icon-md btn-circle" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-edit"></i></span>`
        } else return ''

    }
    $(document).on("click", '.edit-btn', function () {
        $('.modal-title-edit-order').text('Sửa Đơn Hàng ' + $(this).closest('tr').find('.id-idOrdercf-table').text());
        $('.name-product-modal').val($(this).closest('tr').find('.name-product-table').text());
        $('.id-order-modal').val($(this).closest('tr').find('.id-idOrdercf-table').text());
        $('.text-count-edit').val($(this).closest('tr').find('.text-count-table').text());
        $('.status-order-modal').val($(this).closest('tr').find('.status-table').find('.sellect-2').val());
        $('#modal-edit-order').modal('show');
    });
    $(document).on("click", '.save-btn-edit-order', function () {
        $.ajax({
            url: "/admin/ordercf/" + $('.id-order-modal').val(),
            contentType: "application/json",
            type: "PUT",
            data: JSON.stringify({
                quantity: $('.text-count-edit').val(),
                status: $('.status-order-modal').val(),
            }),
            success: function (result) {
                if (result.check === true) {
                    toastr.success("Sửa thành công");
                    $('#modal-edit-order').modal('hide');
                    update_data($("#id-table-select").val());
                } else {
                    Swal.fire({
                        text: result.message,
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok",
                        customClass: {
                            confirmButton: "btn btn-danger"
                        }
                    });
                }
            }
        });
    });
    //handel down-count-edit
    //handle on click save-data
    //handle on click delete-btn
    $(document).on("click", ".delete-btn", function () {
        var idOrdercf = $(this).closest("tr").find(".id-idOrdercf-table").text() || null;
        var row = $(this).closest("tr");

        if (idOrdercf == 'null') {
            toastr.success("Xóa thành công");
            $("#table-order").DataTable().row(row).remove().draw();
        }
        if (idOrdercf != 'null') {
            swal.fire({
                title: "Bạn có chắc chắn muốn xóa?",
                text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Đồng ý",
                cancelButtonText: "Hủy bỏ"
            }).then(function (result) {
                if (result.value) {
                    $.ajax({
                        url: "/admin/ordercf/delete/" + idOrdercf,
                        type: "GET",
                        success: function (result) {
                            if (result.check === true) {
                                toastr.success("Xóa thành công");
                                $("#table-order").DataTable().row(row).remove().draw();
                            } else {
                                toastr.error("Xóa thất bại");
                            }
                        }
                    })
                }
            });
        }

    });

    //handel on change id-select-product
    $(document).on("change", "#id-select-product", function () {
            var id_product = $(this).val();
            var price_product = $(this).find("option:selected").attr("data-price");
            var name_product = $(this).find("option:selected").attr("data-name");
            $(".price-product").val(validatePriceToVND(price_product));
            $(".name-product").val(name_product);
            $(".total-price").val(validatePriceToVND(price_product));
            $(".text-count").val(1);
        }
    );

    let update_data = (table_group_id) => {
        let url;
        if (table_group_id == "all") {
            var layout = $('.add-product-layout');
            layout.hide();
            url = "/admin/ordercf/find-all";
        } else {
            var layout = $('.add-product-layout');
            layout.show();
            url = "/admin/ordercf/find-by-group-table/" + table_group_id;

        }
        var table = $("#table-order").DataTable();
        table.clear().draw();
        $.ajax({
                url: url,
                type: "GET",
                contentType: "application/json",
                success: function (result) {

                    for (let i = 0; i < result.length; i++) {
                        let item = result[i];
                        $.ajax({
                            url: "/admin/ordercf/find-product/" + item.idProduct,
                            type: "GET",
                            contentType: "application/json",
                            success: function (result) {
                                product = result;
                                var total_price = parseInt(product.price) * parseInt(item.quantity);
                                var status = item.status;
                                var option_html = validateSelect(status);
                                var data = {
                                    status: status,
                                    id: item.id,
                                    id_group_table: item.idGroupTable,
                                    group_table_name: null,
                                    idOrdercf: item.idOrdercf,
                                    id_product: item.idProduct,
                                    name_product: product.productName,
                                    count: item.quantity,
                                    time_order: dateTimeSqlServerToDateTime(item.timeOrder),
                                    price_product: product.price,
                                    total_price: total_price,
                                    option_html: option_html,
                                };
                                $.ajax({
                                    url: "/admin/ordercf/find-group-table/" + item.idGroupTable,
                                    type: "GET",
                                    contentType: "application/json",
                                    success: function (result) {
                                        data.group_table_name = result.groupName;
                                        var html = getHTMLRowTabel(data);
                                        $("#table-order").DataTable().row.add($(html)).draw();
                                        updateSelectInTabel();
                                    }
                                });

                            }
                        });


                    }

                }

            }
        );

    }
    //ready function
    $(document).ready(function () {
        var table_group_id = $("#id-table-select").val();
        update_data(table_group_id);
    });
    //handle on change id-table-select
    $(document).on("change", "#id-table-select", function () {
        var table_group_id = $(this).val();
        update_data(table_group_id);
    });
    let dateTimeSqlServerToDateTime = (dateTime) => {
        var date = dateTime.split("T")[0];
        var time = dateTime.split("T")[1].split("+")[0];
        return date + " " + time;
    }
    //handle on change status-table
    $(document).on("change", ".sellect-2", function () {
        var current_status = $(this).closest("tr").find(".status-table").attr("data-status");

        var status = $(this).val();
        var select = $(this);
        if (status == "2") {
            //confirm message
            swal.fire({
                title: "Bạn có chắc chắn muốn xác nhận đã xong không?",
                text: "Sau khi đặt bàn, bạn sẽ không thể phục hồi dữ liệu này!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Đồng ý",
                cancelButtonText: "Hủy bỏ"
            }).then(function (res) {
                if (res.value) {
                    var id = select.closest("tr").find(".id-ordercf-table").text();
                    var id_ordercf = select.closest("tr").find(".id-idOrdercf-table").text();
                    var id_group_table = select.closest("tr").find(".id-group-table").text();
                    var id_product = select.closest("tr").find(".id-product-table").text();
                    var count = select.closest("tr").find(".text-count-table").text();
                    $.ajax({
                        url: "/admin/ordercf/store-final/" + id_ordercf,
                        contentType: "application/json",
                        type: "PUT",
                        data: JSON.stringify({}),
                        success: function (result) {
                            if (result.check === true) {
                                //disable option exept current
                                select.find("option").each(function () {
                                    if ($(this).val() != status) {
                                        $(this).attr("disabled", true);
                                    }
                                });
                                data_invoice = {
                                    idGroupTable: id_group_table,
                                }
                                $.ajax({
                                    url: "/admin/invoice/store-one",
                                    method: "POST",
                                    contentType: "application/json",
                                    data: JSON.stringify(data_invoice),
                                    success: function (result) {
                                        var idInvoice = result;
                                        var data_invoice_details = {
                                            idInvoice: idInvoice,
                                            idProduct: id_product,
                                            quantity: count,
                                        }
                                        console.log(JSON.stringify(data_invoice_details))
                                        $.ajax(
                                            {
                                                url: "/admin/detailInvoice/store",
                                                method: "POST",
                                                contentType: "application/json",
                                                data: JSON.stringify(data_invoice_details),
                                                success: function (result) {
                                                    console.log(result);
                                                    if (result.check == true) {
                                                        select.select2("val", current_status);
                                                        var table_group_id = $("#id-table-select").val();
                                                        toastr.success("Cập nhật thành công");
                                                        update_data(table_group_id);
                                                    } else {
                                                        select.select2("val", current_status);
                                                        toastr.error("Cập nhật thất bại");
                                                    }
                                                }
                                            }
                                        )
                                    }
                                })

                            } else {
                                toastr.error("Cập nhật thất bại");
                            }
                        }
                    });
                } else if (res.dismiss == 'cancel') {
                    select.select2("val", current_status);
                }
            });
        } else {
            $(this).closest("tr").find(".status-table").attr("data-status", status);
        }
        if (status == "3") {
            var id_ordercf = select.closest("tr").find(".id-idOrdercf-table").text();
            swal.fire({
                title: "Bạn có chắc chắn muốn hủy đơn hàng này không?",
                text: "Sau khi hủy đơn hàng, bạn sẽ không thể phục hồi dữ liệu này!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Đồng ý",
                cancelButtonText: "Hủy bỏ"
            }).then(function (res) {
                if (res.value) {
                    var id_ordercf = select.closest("tr").find(".id-idOrdercf-table").text();
                    $.ajax(
                        {
                            url: '/admin/ordercf/' + id_ordercf,
                            method: "PUT",
                            contentType: "application/json",
                            data: JSON.stringify({
                                status: status
                            }),
                            success: function (result) {
                                if (result.check == true) {
                                    select.select2("val", current_status);
                                    var table_group_id = $("#id-table-select").val();
                                    toastr.success("Cập nhật thành công");
                                    update_data(table_group_id);
                                } else {
                                    select.select2("val", current_status);
                                    toastr.error("Cập nhật thất bại");
                                }
                            }
                        }
                    )
                } else if (res.dismiss == 'cancel') {
                    select.select2("val", current_status);
                }
            });
        }
    });




</script>