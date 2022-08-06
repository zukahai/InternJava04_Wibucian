<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <span class="btn btn-primary align-self-center save-data">Lưu thông tin</span>
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
                    <c:forEach items="${groupTables}" var="item">
                        <option value="${item.id}">${item.groupName}</option>
                    </c:forEach>

                </select>
            </div>
            <div class="row  d-flex  justify-content-between">
                <div class="mb-5 col">
                    <select id="id-select-product" class="form-select form-select-solid" data-control="select2"
                            data-placeholder="Chọn sản phẩm">
                        <c:forEach items="${products}" var="product">
                            <option data-name="${product.productName}" data-price="${product.price}"
                                    value="${product.id}">${product.productName}</option>
                        </c:forEach>
                    </select>

                </div>
                <div class="col ">
                    <div class="input-group mb-5">
                        <span class="input-group-text me-2">Giá</span>
                        <input readonly type="text" value="<c:out value="${products[0].price}"></c:out>"
                               class="form-control text-center price-product"
                               id="price-prod"/>
                    </div>
                </div>
                <div class="col col-3">
                    <div class="input-group mb-5">
                        <span class="input-group-text me-2" id="basic-addon3">Số lượng</span>
                        <span class="input-group-text btn  btn-dark text-center  down-count " id="down-count">-</span>
                        <input type="text" value="0" class="form-control text-center text-count" id="count"/>
                        <span class="input-group-text btn  btn-dark text-center  up-count " id="up-count">+</span>
                    </div>
                </div>
                <div class="col ">
                    <div readonly="true" class="input-group mb-5">
                        <span class="input-group-text">Tổng tiền</span>
                        <input readonly value="0" type="text" class="form-control total-price" id="total-price"
                               aria-describedby="basic-addon3"/>
                    </div>
                </div>
                <div class="col">
                    <spam class="btn btn-primary align-self-center add-product-order">Thêm sản phẩm</spam>
                </div>
            </div>
            <hr>
            <h3>Danh sách sản phẩm order</h3>
            <div class="d-flex flex-stack ">

                <table class="table align-middle table-row-dashed fs-6 gy-5" id="tabel-order">
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

                        <th class="min-w-125px">Id Sản phẩm</th>
                        <th class="min-w-125px">Tên sản phẩm</th>
                        <th class="min-w-125px">Số lượng</th>
                        <th class="min-w-125px">Giá</th>
                        <th class="min-w-125px">Tổng tiền</th>
                        <th class="min-w-125px">Hành động</th>
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
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script !src="">
    $("#tabel-order").DataTable({});
    //handle click on .delete-btn button

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
        total_price.val(total_price_value);
    });
    $(document).on("click", ".down-count", function () {
        var count = parseInt($(this).siblings(".text-count").val());
        if (count > 0) {
            count--;
            $(this).siblings(".text-count").val(count);
            var price_product = $(".price-product");
            var total_price = $(".total-price");
            var total_product = $(".total-product");
            var price_product_value = validatePrice(price_product.val());
            var total_price_value = count * price_product_value;
            total_price.val(total_price_value);
        }
    });

    function validatePrice(price) {
        //delete all character except number script
        var price_new = price.replace(/[^0-9]/g, '');
        return price_new;
    }

    $(document).on("click", ".down-up-tabel", function () {
        var count_text = $(this).siblings(".text-count-tabel");
        var total_price = $(this).closest("tr").find(".total-price-tabel");
        var price_product = $(this).closest("tr").find(".price-product-tabel");
        var count = parseInt(count_text.val());
        count_text.val(count + 1);
        var price = validatePrice(price_product.text());
        total_price.text(price * (count + 1));

    });
    $(document).on("click", ".down-count-tabel", function () {
        var count_text = $(this).siblings(".text-count-tabel");
        var total_price = $(this).closest("tr").find(".total-price-tabel");
        var price_product = $(this).closest("tr").find(".price-product-tabel");
        var count = parseInt(count_text.val());
        if (count > 0) {
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
    //handle on type in text-count-tabel change value of total-price
    $(document).on("input", ".text-count-tabel", function () {
        var count = parseInt($(this).val());
        $(this).siblings(".text-count-tabel").val(count);
        var price_product = $(this).closest("tr").find(".price-product-tabel");
        var total_price = $(this).closest("tr").find(".total-price-tabel");
        var price_product_value = validatePrice(price_product.text());
        var total_price_value = count * price_product_value;
        total_price.text(total_price_value);
    });
    //handel add-product-order button
    $(document).on("click", ".add-product-order", function () {
        var count = parseInt($(".text-count").val());
        var price_product = $(".price-product").val();
        var total_price = $(".total-price").val();
        var id_product = $("#id-select-product").val();
        var name_product = $("#id-select-product option:selected").attr("data-name");
        var tr_in_tabel = $("#tabel-order tbody tr");
        //get row in tabel have id_product
        var row_in_tabel = tr_in_tabel.filter(function () {
            return $(this).find(".id-product-tabel").text() == id_product;
        });
        //update count and total price in row have id_product
        if (row_in_tabel.length > 0) {
            //update with DataTable
            var count_in_tabel = row_in_tabel.find(".text-count-tabel");
            var total_price_in_tabel = row_in_tabel.find(".total-price-tabel");
            count_in_tabel.val(parseInt(count_in_tabel.val()) + parseInt(count));
            total_price_in_tabel.text(parseInt(total_price_in_tabel.text()) + parseInt(total_price));
        } else {
            var html = `<tr>
                        <td>
                            <div class="form-check form-check-sm form-check-custom form-check-solid">
                                <input class="form-check-input" type="checkbox" value="1"/>
                            </div>
                        </td>
                        <td style="display: none"><p class="id-ordercf-tabel"></p></td>
                        <td style="display: none"><p class="id-idOrdercf-tabel"></p></td>
                        <td><p class="id-product-tabel">` + id_product + `</p></td>
                        <td><p class="name-product-tabel">` + name_product + `</p></td>
                        <td class="w-175px">
                            <div class="input-group mb-5">
                                <span class="input-group-text btn btn-dark text-center down-count-tabel">-</span>
                                <input type="text" value="` + count + `" class="form-control text-center text-count-tabel"/>
                                <span class="input-group-text btn btn-dark text-center down-up-tabel ">+</span>
                            </div>
                        </td>
                        <td><p class="price-product-tabel">` + price_product + `</p></td>
                        <td class="total-price-tabel"><p class="total-product-table">` + total_price + `</p></td>
                        <td class="">
                            <span class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                  data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                        </td>
                    </tr>`;
            $("#tabel-order").DataTable().row.add($(html)).draw();
        }


    });
    //handle on click save-data
    $(document).on("click", ".save-data", function () {
        ///get data from tabel-order
        var tabel = $("#tabel-order tbody");
        let data = [];
        tabel.find("tr").each(function () {
            var id_product = $(this).find(".id-product-tabel").text();
            var name_product = $(this).find(".name-product-tabel").text();
            var count = $(this).find(".text-count-tabel").val();
            var price_product = $(this).find(".price-product-tabel").text();
            var total_price = $(this).find(".total-price-tabel").text();
            var id_order = $(this).find(".id-ordercf-tabel").text();
            var idOrdercf = $(this).find(".id-idOrdercf-tabel").text();
            var tabel_group_id = $("#id-table-select").val();
            data.push({
                id: id_order || null,
                idOrdercf: idOrdercf || null,
                idGroupTable: tabel_group_id,
                idProduct: id_product,
                quantity: count,
            });
        });
        console.log(JSON.stringify(data));

        //send data to server
        $.ajax({
            url: "/ordercf/store",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check === true) {
                    toastr.success("Thêm thành công");
                    $("#tabel-order").DataTable().clear().draw();
                } else {
                    toastr.error("Thêm thất bại");
                }
            }
        })


        //handle on click delete-btn
        $(document).on("click", ".delete-btn", function () {
            //confirm delete alert
            swal.fire(
                {
                    title: "Bạn có chắc chắn muốn xóa?",
                    text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Xóa",
                    cancelButtonText: "Hủy"
                }
            ).then((result) => {
                if (result.value) {
                    var row = $(this).closest("tr");
                    $("#tabel-order").DataTable().row(row).remove().draw();
                }
            });
        });
    });

    //handel on change id-select-product
    $(document).on("change", "#id-select-product", function () {
            var id_product = $(this).val();
            var price_product = $(this).find("option:selected").attr("data-price");
            var name_product = $(this).find("option:selected").attr("data-name");
            $(".price-product").val(price_product);
            $(".name-product").val(name_product);
            $(".total-price").val(0);
            $(".text-count").val(0);
        }
    );
</script>
<script>
    let update_data = (tabel_group_id) => {
        var tabel = $("#tabel-order").DataTable();
        tabel.clear().draw();
        $.ajax({
                url: "/ordercf/findByGroupTableId/" + tabel_group_id,
                type: "GET",
                contentType: "application/json",
                success: function (result) {
                    result.forEach(function (item) {
                        var product;
                        $.ajax({
                            url: "/ordercf/findByProductId/" + item.idProduct,
                            type: "GET",
                            contentType: "application/json",
                            success: function (result) {
                                product = result;
                                console.log(product.price);
                                var total_price = parseInt(product.price) * parseInt(item.quantity);
                                var html = `<tr>
                        <td>
                            <div class="form-check form-check-sm form-check-custom form-check-solid">
                                <input class="form-check-input" type="checkbox" value="1"/>
                            </div>
                        </td>
                        <td style="display: none"><p class="id-ordercf-tabel">` + item.id + `</p></td>
                        <td style="display: none"><p class="id-idOrdercf-tabel">` + item.idOrdercf + `</p></td>
                        <td><p class="id-product-tabel">` + item.idProduct + `</p></td>
                        <td><p class="name-product-tabel">` + product.productName + `</p></td>
                        <td class="w-175px">
                            <div class="input-group mb-5">
                                <span class="input-group-text btn btn-dark text-center down-count-tabel">-</span>
                                <input type="text" value="` + item.quantity + `" class="form-control text-center text-count-tabel"/>
                                <span class="input-group-text btn btn-dark text-center down-up-tabel ">+</span>
                            </div>
                        </td>
                        <td><p class="price-product-tabel">` + product.price + `</p></td>
                        <td class="total-price-tabel"><p class="total-product-table">` + total_price + `</p></td>
                        <td class="">
                            <span class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                  data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                        </td>
                    </tr>`;
                                $("#tabel-order").DataTable().row.add($(html)).draw();
                            }
                        });

                    });
                }

            }
        );
    }
    //ready function
    $(document).ready(function () {
        var tabel_group_id = $("#id-table-select").val();
        update_data(tabel_group_id)
    });
    //handle on change id-table-select
    $(document).on("change", "#id-table-select", function () {
        var tabel_group_id = $(this).val();
        console.log(tabel_group_id);
        update_data(tabel_group_id)
    });
</script>