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
                        <input type="text" value="1" class="form-control text-center text-count" id="count"/>
                        <span class="input-group-text btn  btn-dark text-center  up-count " id="up-count">+</span>
                    </div>
                </div>
                <div class="col ">
                    <div readonly="true" class="input-group mb-5">
                        <span class="input-group-text">Tổng tiền</span>
                        <input readonly  value="<c:out value="${products[0].price}"></c:out>" type="text" class="form-control total-price" id="total-price"
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
