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
                <h3 class="fw-bold m-0">Thêm mới sản phẩm</h3>
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
            <form action="/admin/product" method="post" enctype="multipart/form-data">
                <div class="rounded border p-5">

                    <div class="row">
                        <div class="col">
                            <div class="fv-row mb-7">
                                <label class="d-block fw-semibold fs-6 mb-5">Ảnh</label>
                                <style>.image-input-placeholder {
                                    background-image: url(/admin/assets/img/avatar.jpg);
                                }

                                [data-theme="dark"] .image-input-placeholder {
                                    background-image: url(/admin/assets/img/avatar.jpg);
                                }</style>
                                <div class="image-input image-input-outline image-input-placeholder"
                                     data-kt-image-input="true">
                                    <div class="image-input-wrapper w-150px h-150px"
                                         style="background-image: url(/admin/assets/img/avatar.jpg);"></div>
                                    <label class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                           data-kt-image-input-action="change" data-bs-toggle="tooltip"
                                           title="Change avatar">
                                        <i class="bi bi-pencil-fill fs-7"></i>
                                        <input type="file" name="avatar" accept=".png, .jpg, .jpeg"/>
                                        <input type="hidden" name="srcImage"/>
                                    </label>
                                    <span class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                          data-kt-image-input-action="cancel" data-bs-toggle="tooltip"
                                          title="Cancel avatar">
																				<i class="bi bi-x fs-2"></i>
																			</span>
                                    <i class="bi bi-x fs-2"></i>
                                    </span>
                                </div>
                                <div class="form-text">Allowed file types: png, jpg, jpeg.</div>
                                <!--end::Hint-->
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-floating my-5">
                                <input type="text" class="form-control" id="productName" name="productName"
                                       placeholder="VIP"/>
                                <label for="productName">Tên sản phẩm</label>
                            </div>
                            <div class="form-floating my-5">
                                <select class="form-select" data-control="select2" id="idTypeProduct"
                                        name="idProductType" data-placeholder="Select an option">
                                    <c:forEach var="item" items="${typeProduct}">
                                        <option value="${item.id}">${item.id} - ${item.productName}</option>
                                    </c:forEach>
                                </select>
                                <label for="idTypeProduct">Loại sản phẩm</label>
                            </div>
                            <div class="form-floating my-5">
                                <select class="form-select" data-control="select2" id="idSale" name="idSale"
                                        data-placeholder="Select an option">
                                    <c:forEach var="item" items="${sale}">
                                        <option value="${item.id}">${item.id} - ${item.pcent}</option>
                                    </c:forEach>
                                </select>
                                <label for="idSale">Mã sản phẩm Sale</label>
                            </div>
                            <div class="form-floating my-5">
                                <input type="text" class="form-control" id="describe" name="describe"
                                       placeholder="VIP"/>
                                <label for="productName">Mô tả</label>
                            </div>
                        </div>

                    </div>


                </div>
                <div class="text-center my-5">
                    <button class="btn btn-primary" type="submit">Thêm</button>
                </div>
            </form>
        </div>
        <!--end::Card body-->
    </div>
</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>