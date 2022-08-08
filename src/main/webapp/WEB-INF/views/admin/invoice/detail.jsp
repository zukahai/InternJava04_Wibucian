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
            <h3 class="fw-bold m-0">Order</h3>
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
        <div class="mb-10">
            <label for="id-invoice" class="required form-label">Example</label>
            <input id="id-invoice" type="text" value="${invoice.id}" readonly class="form-control form-control-solid" placeholder="Example input"/>
        </div>
        <div class="mb-10">
            <label for="name-employee-invoice" class="required form-label">Example</label>
            <input data-value="${invoice.employee.name}" id="name-employee-invoice" type="text" value="${invoice.id}" readonly class="form-control form-control-solid" placeholder="Example input"/>
        </div>
        <h3>Danh sách sản phẩm order</h3>
        <div class="d-flex flex-stack ">

            <table class="table" id="tabel-invoice">
                <!--begin::Table head-->
                <thead>
                <!--begin::Table row-->
                <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                    <th scope="col" class="min-w-100px">Id chi tiết hoá đơn</th>
                    <th scope="col" class="min-w-100px">Tên sản phẩm</th>
                    <th scope="col" class="min-w-100px">Số lượng</th>
                    <th scope="col" class="min-w-100px">Tổng tiền</th>
                    <th scope="col" class="min-w-100px">Hành động</th>
                </tr>
                <!--end::Table row-->
                </thead>

                <tbody class="text-gray-600 fw-semibold">
                <c:forEach items="${invoice.detailInvoices}" var="item">
                    <tr class="text-start">
                        <td><p class="id-detail-invoice-table">${item.id}</p></td>
                        <td><p data-id="item.product.id" class="id-product-tabel">${item.product.productName}</p></td>
                        <td><p class="quantity-table">${item.quantity}</p></td>
                        <td>${item.totalMoney}</td>
                        <td>
                            <a href="/invoice/detail/${item.id}" data-action="${item.id}" class="btn btn-icon btn-primary  btn-sm btn-icon-md btn-circle"
                               data-toggle="tooltip" data-placement="top" title="Sửa">
                                <i class="fa fa-edit"></i>
                            </a>
                            <span data-action="${item.id}" class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                  data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script>

</script>