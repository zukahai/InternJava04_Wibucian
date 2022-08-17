<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<div class="content flex-column-fluid" id="kt_content">

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <!--begin::Card header-->
        <div class="card-header cursor-pointer">
            <!--begin::Card title-->
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Chỉnh sửa</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a href="admin/product/" class="btn btn-primary align-self-center">Danh sách sản phẩm</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <form action="/admin/product/edit/${product.id}" method="post" enctype="multipart/form-data">
                <div class="rounded border p-5">
                    <div class="form-floating my-5">
                        <input type="idProduct" class="form-control" id="idProduct" name="idProduct" placeholder="0" value="${product.id}" readonly/>
                        <label for="idProduct">Mã sản phẩm</label>
                    </div>
                    <div class="form-floating my-5">
                        <input type="productName" class="form-control" id="productName" name="productName" placeholder="0" value="${product.productName}" />
                        <label for="idProduct">Tên sản phẩm</label>
                    </div>

                    <div class="form-floating my-5">
                        <select class="form-select" data-control="select2" id="idProductType" name="idProductType" data-placeholder="Select an option">
                            <c:forEach var="item" items="${typeProduct}">
                                <option value="${item.id}" ${(item.id == product.productType.id) ? "selected" : ""}> ${item.id} - ${item.productName} </option>
                            </c:forEach>
                        </select>
                        <label for="idProductType">Loại sản phẩm</label>
                    </div>
<%--                    <div class="form-floating my-5">--%>
<%--                        <select class="form-select" data-control="select2" id="idSale" name="idSale" data-placeholder="Select an option">--%>
<%--                            <c:forEach var="item" items="${sale}">--%>
<%--                                <option value="${item.id}" ${(item.id == sale.id) ? "selected" : ""}> ${item.id} </option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                        <label for="idProductType">Loại Giảm Giá</label>--%>
<%--                    </div>--%>
                    <div class="form-floating my-5">
                        <input type="describe" class="form-control" id="describe" name="describe" placeholder="0" value="${product.describe}"/>
                        <label for="describe">Mô tả</label>
                    </div>
                    <div class="d-flex flex-center flex-column py-5">
                        <!--begin::Avatar-->
                        <div class="fv-row mb-7">
                            <label class="d-block fw-semibold fs-6 mb-5">Ảnh</label>
                            <style>.image-input-placeholder {
                                background-image: url(/admin/assets/file-upload/);
                            }

                            [data-theme="dark"] .image-input-placeholder {
                                background-image: url(/admin/assets/file-upload/);
                            }</style>
                            <div class="image-input image-input-outline image-input-placeholder" data-kt-image-input="true">
                                <div class="image-input-wrapper w-150px h-150px"
                                     style="background-image: url(/admin/assets/file-upload/${product.srcImage}");"></div>
                            <label class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                   data-kt-image-input-action="change" data-bs-toggle="tooltip" title="Change avatar">
                                <i class="bi bi-pencil-fill fs-7"></i>
                                <input type="file"  name="avatar" accept=".png, .jpg, .jpeg"/>
                                <input type="hidden" name="srcEmployee"/>
                            </label>
                            <span class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                  data-kt-image-input-action="cancel" data-bs-toggle="tooltip" title="Cancel avatar">
																				<i class="bi bi-x fs-2"></i>
																			</span>
                            <i class="bi bi-x fs-2"></i>
                            </span>
                        </div>
                        <div class="form-text">Allowed file types: png, jpg, jpeg.</div>
                        <!--end::Hint-->
                    </div>
                    <!--end::Avatar-->
                    <!--begin::Name-->
                </div>

                <div class="text-center my-5">
                    <button class="btn btn-success" type="submit">Sửa</button>
                </div>
            </form>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>