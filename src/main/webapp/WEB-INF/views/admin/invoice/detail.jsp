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
            <h3 class="fw-bold m-0"><a href="${pageContext.request.contextPath}/invoice">Danh sách hoá đơn</a></h3>
        </div>
        <!--end::Card title-->
        <!--begin::Action-->
        <c:if test="${invoice.status != 2}">
            <span class="btn btn-primary align-self-center invoice-save">
                <i class="fa fa-file-invoice"></i>
                Thanh Toán
            </span>
        </c:if>
        <!--end::Action-->
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9">
        <!--begin::Wrapper-->
        <div class="card p-9">
            <div hidden class="mb-10">
                <label for="id-invoice" class="required form-label">Id Hoá Đơn</label>
                <input id="id-invoice" type="text" value="${invoice.id}" readonly
                       class="form-control id-invoice form-control-solid"
                       placeholder="Example input"/>
            </div>
            <div class="mb-10">
                <label for="name-employee-invoice" class="required form-label">Nhân viên</label>
                <input data-value="${invoice.employee.name}" id="name-employee-invoice" type="text"
                       value="${invoice.employee.name}" readonly class="form-control form-control-solid"
                       placeholder="Example input"/>
            </div>
            <c:if test="${invoice.status == 2}">
                <div class="mb-10">
                    <label for="name-employee-invoice" class="required form-label">Ngày thanh toán</label>
                    <input data-value="${invoice.dateTime}" id="date-bill-invoice" type="text"
                           value="${invoice.dateTime}" readonly class="form-control form-control-solid"
                           placeholder="Example input"/>
                </div>
            </c:if>

            <div class="mb-10 form-inline ">
                <label for="name-customer-invoice" class="required form-label">Tên khách hàng</label>
                <div class="input-group">
                    <input data-value="${invoice.customerName}" id="name-customer-invoice" type="text"
                           value="${invoice.customerName}" class="form-control form-control-solid name-customer-invoice"
                           placeholder="Example input"/>
                    <span class="btn btn-primary align-self-center save-name-invoice">Lưu Tên</span>
                </div>
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
                <c:if test="${invoice.status != 2}">
                    <span class="btn btn-primary align-self-center add-detail"><i
                            class="fa fa-plus"></i>Thêm chi tiết</span>
                </c:if>
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
                            <th scope="col" class="min-w-100px"><h5>Tên sản phẩm</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Số lượng</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Giảm giá</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Giá gốc</h5></th>
                            <th scope="col" class="min-w-100px"><h5>Giá sau khi giảm</h5></th>
                            <c:if test="${invoice.status != 2}">
                                <th scope="col" class="min-w-100px"><h4>Hành động</h4></th>
                            </c:if>
                        </tr>
                        <!--end::Table row-->
                        </thead>

                        <tbody class="text-gray-600 fw-semibold">
                        <c:forEach items="${invoice.detailInvoices}" var="item">

                            <tr class="text-start id-detail-invoice-${item.id}">
                                <td><p data-price="${item.product.price}" data-id="item.product.id"
                                       class="id-product-tabel">${item.product.productName}</p></td>
                                <td><p class="quantity-table">${item.quantity}</p></td>
                                <c:set var="totalPrice" value="${item.quantity * item.product.price}"/>
                                <c:set var="totalPriceDiscount" value="${item.totalMoney}"/>
                                <c:set var="total" value="${item.product.price * item.quantity}"/>
                                <c:set var="pcent" value="${(total-totalPriceDiscount)/total*100}"/>
                                <td><p class="sale-table">${pcent} %</p></td>
                                <td>
                                    <p class="total-table text-d"><s><fmt:formatNumber pattern="#,###"
                                                                                       value="${total}"/> ₫</s></p>
                                </td>
                                <td><p data-price="${item.totalMoney}" class="total-money-price">
                                    <fmt:formatNumber pattern="#,###" value="${item.totalMoney}"/> ₫</p>
                                    <c:if test="${invoice.status != 2}">
                                <td>
                                <span data-action="${item.id}"
                                      class="btn btn-icon btn-primary btn-edit  btn-sm btn-icon-md btn-circle"
                                      data-toggle="tooltip" data-placement="top" title="Sửa">
                                    <i class="fa fa-edit"></i>
                                </span>
                                    <span data-action="${item.id}"
                                          class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                          data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                                </td>
                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>

                        <tfoot>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><h3>Tổng Tiền</h3></td>
                            <td><h3 data-price="${invoice.totalMoney}"
                                    class="total-money-price-end"><fmt:setLocale
                                    value="vi_VN"/>
                                <fmt:formatNumber pattern="#,###" value="${invoice.totalMoney}"/> ₫</h3></td>
                        </tr>
                        </tfoot>

                    </table>
                    <div class="card">
                        <div class="card-header">
                            <!--begin::Card title-->
                            <div class="card-title m-0">
                                <h3 class="fw-bold m-0"></h3>
                            </div>
                            <!--end::Card title-->
                            <!--begin::Action-->
                            <c:if test="${invoice.status != 2}">
                                <span class="btn btn-primary align-self-center invoice-save">
                                    <i class="fa fa-file-invoice"></i>
                                    Thanh Toán
                                </span>
                            </c:if>
                            <c:if test="${invoice.status == 2}">
                                <div>
                                    <span class="btn btn-primary align-self-center invoice-qr-code">
                                    <i class="fa fa-file-invoice"></i>
                                    Xem lại thanh toán
                                </span>
                                    <span class="btn btn-primary align-self-center invoice-print">
                                    <i class="fa fa-print"></i>
                                    In hóa đơn
                                </span>
                                </div>
                            </c:if>

                        </div>

                        <div class="card-body">

                        </div>

                    </div>
                </div>
                <!--end::Wrapper-->
            </div>
        </div>
    </div>
    <div id="modal-edit-detail" class="modal fade" tabindex="-1">
        <div class=" modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title"></h3>
                    <!--begin::Close-->
                    <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                         aria-label="Close">
                        <span class="svg-icon svg-icon-1"></span>
                    </div>
                    <!--end::Close-->
                </div>
                <div class="modal-body">
                    <div class="mb-10">
                        <label class="required form-label ">Id Hoá Đơn</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid id-detail-invoice"/>
                    </div>
                    <div class="mb-10">
                        <label class="required form-label">Tên sản phẩm</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid id-product-invoice"/>
                    </div>
                    <div class="input-group mb-5">
                        <span class="input-group-text me-2">Số lượng</span>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count ">-</span>
                        <input type="number" value="1" class="form-control text-center text-count "/>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark  up-count btn-sm btn-icon-md btn-circle ">+</span>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary save-btn">Lưu lại</button>
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-add-detail" role="dialog" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title-add-detail"></h3>
                    <!--begin::Close-->
                    <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                         aria-label="Close">
                        <span class="svg-icon svg-icon-1"></span>
                    </div>
                    <!--end::Close-->
                </div>
                <div class="modal-body">
                    <div hidden class="mb-10">
                        <label class="required form-label">Id Hoá Đơn</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid id-invoice-modal" placeholder="Example input"/>
                    </div>
                    <div class="mb-10">
                        <div class="mb-10">
                            <label class="required form-label">Chọn Sản Phẩm</label>
                            <select class="form-select select-2-product form-select-solid" data-control="select2"
                                    data-placeholder="Chọn sản phẩm">
                                <c:forEach items="${products}" var="product">
                                    <option data-name="${product.productName}" data-price="${product.price}"
                                            value="${product.id}">${product.productName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="input-group mb-5">
                        <span class="input-group-text me-2" id="basic-addon3">Số lượng</span>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark btn-sm btn-icon-md btn-circle down-count "
                              id="down-count">-</span>
                        <input type="number" value="1" class="form-control text-center text-count " id="count"/>
                        <span class="input-group-text btn  btn-light-secondary btn-outline text-center text-dark  up-count btn-sm btn-icon-md btn-circle "
                              id="up-count">+</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary save-btn-add-detail">Lưu lại</button>
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <div id="modal-show-qr-code" class="modal fade" tabindex="-1">
        <div class=" modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title"></h3>
                    <!--begin::Close-->
                    <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal"
                         aria-label="Close">
                        <span class="svg-icon svg-icon-1"></span>
                    </div>
                    <!--end::Close-->
                </div>
                <div class="modal-body">
                    <div class="mb-10">
                        <label class="required form-label ">Tên Khách Hàng</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid customer-detail-invoice"/>
                    </div>
                    <div class="mb-10">
                        <label class="required form-label">Tổng số tiền</label>
                        <input data-action="" readonly type="text"
                               class="form-control form-control-solid total-price-invoice"/>
                    </div>
                    <div class="d-flex justify-content-center shadow-lg">
                        <img src="" class="qr-code-modal" alt="">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary save-btn">Lưu lại</button>
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
                    url: "/admin/detailInvoice/delete/" + id,
                    type: "GET",
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
        $('.modal-title').text("Sửa chi tiết hoá đơn " + $(this).attr("data-action"));
        $('.id-detail-invoice').val($(this).attr("data-action"));
        $('.id-detail-invoice').attr("data-action", $(this).attr("data-action"));
        $('.id-product-invoice').val($(this).closest("tr").find(".id-product-tabel").text());
        $('.text-count').val($(this).closest("tr").find(".quantity-table").text());
        $('#modal-edit-detail').modal('show');
    });
    //hadle up-count
    $(document).on("click", ".up-count", function () {
        var count = $('.text-count').val();
        count++;
        $('.text-count').val(count);
    });
    //hadle down-count
    $(document).on("click", ".down-count", function () {
        var count = $('.text-count').val();
        if (count > 1) {
            count--;
            $('.text-count').val(count);
        }
    });
    //handel save-btn
    $(document).on("click", ".save-btn", function () {
        var id = $('.id-detail-invoice').attr("data-action");
        var quantity = $('.text-count').val();
        let data = {
            idDetailInvoice: id,
            quantity: quantity
        };
        $.ajax({
            url: "/admin/detailInvoice/" + id,
            contentType: "application/json",
            type: "PUT",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.check == true) {
                    toastr.success("Cập nhật thành công");
                    //find row and update
                    var row = $('.id-detail-invoice-' + id);
                    row.find(".quantity-table").text(quantity);
                    row.find(".total-money-price").text(validatePriceToVND(parseInt(quantity) * parseInt(row.find(".id-product-tabel").attr("data-price"))));
                    toastr.success("Cập nhật thành công");
                    $('#modal-edit-detail').modal('hide');
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
    $('.select-2-product').select2();
    //handel add-detail
    $(document).on("click", ".add-detail", function () {
        $('.modal-title-add-detail').text("Thêm chi tiết hoá đơn");
        $('.id-invoice-modal').val($('.id-invoice').val());
        // $('.text-count').val("1");
        $('#modal-add-detail').modal('show');
    });
    //handel save-btn-add-detail
    $(document).on("click", ".save-btn-add-detail", function () {
        var id = $('.id-invoice-modal').val();
        var quantity = $('.text-count').val();
        var idProduct = $('.select-2-product').val();
        let data = {
            idInvoice: id,
            idProduct: idProduct,
            quantity: quantity
        }
        console.log(JSON.stringify(data));
        $.ajax({
            url: "/admin/detailInvoice/store",
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
            url: "/admin/invoice/" + id,
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
            url: "/admin/invoice/" + id,
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
    $(document).on("click", '.invoice-print', function (){
        //click print-datatable
        $('.print-datatable').click();
    })

</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/2.3.2/jspdf.plugin.autotable.js"></script>
<script>
    $('#tabel-invoice').DataTable({
        <c:if test="${invoice.status == 2}">
        dom: 'Bfrtip',
        //custom pdf button

        buttons: [
            {
                //hide button print
                extend: 'pdfHtml5',
                text: '<i  class="fa fa-print print-datatable"></i>',
                titleAttr: 'PDF',
                footer: true,
                title: 'Danh sách đơn hàng ' + $('#name-customer-invoice').val() + '\n\n' + 'Tổng số tiền: ' + $('.total-money-price-end').text().trim(),
                //add total price

                customize: function (doc) {

                    doc.styles.tableHeader = {
                        color: 'black',
                        background: 'grey',
                        alignment: 'center'
                    }
                    doc.styles = {
                        subheader: {
                            fontSize: 10,
                            bold: true,
                            color: 'black'
                        },
                        tableHeader: {
                            bold: true,
                            fontSize: 10.5,
                            color: 'black'
                        },
                        lastLine: {
                            bold: true,
                            fontSize: 11,
                            color: 'blue'
                        },
                        defaultStyle: {
                            fontSize: 10,
                            color: 'black'
                        }
                    }
                    var objLayout = {
                        hLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 2 : 1;
                        },
                        vLineWidth: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 2 : 1;
                        },
                        hLineColor: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 'black' : 'black';
                        },
                        vLineColor: function (i, node) {
                            return (i === 0 || i === node.table.body.length) ? 'black' : 'black';
                        }
                    }
                    //add doc Tổng số tiền ở footer

                    doc.content[1].layout = objLayout;


                },


            },

        ]
        </c:if>


    });

</script>