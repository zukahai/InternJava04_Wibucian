<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<div class="content flex-column-fluid" id="kt_content">

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <div class="card-header cursor-pointer">
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Thêm Nguyên Liệu</h3>
            </div>
            <a href="admin/ingredient/" class="btn btn-primary align-self-center">Danh sách nguyên liệu</a
            >
        </div>
        <div class="card-body p-9">
            <form action="/admin/ingredient/" method="post" enctype="multipart/form-data">

                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="ingredientName" name="ingredientName" placeholder="VIP"/>
                    <label for="ingredientName">Tên nguyên liệu </label>
                </div>
                <div class="form-floating my-5">
                    <input type="date" class="form-control" id="expiryIngredient" name="expiryIngredient" placeholder="VIP"/>
                    <label for="expiryIngredient">Hạn sử dụng</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="price" name="price" placeholder="VIP"/>
                    <label for="price">Giá</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="origin" name="origin" placeholder="VIP"/>
                    <label for="origin">Nguồn gốc</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="unit" name="unit" placeholder="VIP"/>
                    <label for="unit">Đơn vị</label>
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