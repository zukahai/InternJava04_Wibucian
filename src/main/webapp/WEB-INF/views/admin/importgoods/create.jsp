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
                <h3 class="fw-bold m-0">Thêm nhập hàng</h3>
            </div>
            <a href="admin/importgoods/" class="btn btn-primary align-self-center">Danh sách nhập hàng</a
            >
        </div>
        <div class="card-body p-9">
            <form action="/admin/importgoods/" method="post" enctype="multipart/form-data">

                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="idEmployee" name="idEmployee" placeholder="VIP"/>
                    <label for="idEmployee">Nhân viên nhập hàng </label>
                </div>
                <div class="form-floating my-5">
                    <input type="date" class="form-control" id="timeImport" name="timeImport" placeholder="VIP"/>
                    <label for="timeImport">Thời gian nhập hàng</label>
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
            <!--end::Action-->
<%--        </div>--%>
<%--        <!--begin::Card header-->--%>
<%--        <!--begin::Card body-->--%>
<%--        <div class="card-body p-9">--%>
<%--            <div>--%>
<%--             --%>
<%--                <div>--%>
<%--                    <button type="button" class="add-ingredient">Thêm nguyên liệu</button>--%>
<%--                </div>--%>
<%--                &lt;%&ndash;                <form class="inner-form">&ndash;%&gt;--%>
<%--                <form action="admin/importgoods/create" method="post" class="inner-form" style="visibility: hidden">--%>
<%--                    <input type="text" name="name" placeholder="Nhập tên nguyên liệu"/>--%>
<%--                    <input type="text" name="price" placeholder="Nhập giá nguyên liệu"/>--%>
<%--                    <input type="text" name="origin" placeholder="Nhập nguồn gốc nguyên liệu"/>--%>
<%--                    <input type="text" name="unit" placeholder="Nhập đơn vị"/>--%>
<%--                    <input type="date" name="expireDate" placeholder="Hạn sử dụng"/>--%>
<%--                    <button type="submit">Thêm</button>--%>
<%--                    <button type="reset" class="inner-form-hide">Ẩn</button>--%>
<%--                </form>--%>
<%--                &lt;%&ndash;                </form>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <!--end::Card body-->--%>
<%--    </div>--%>
<%--</div>--%>
<%--<jsp:include page="../includes/footer.jsp"></jsp:include>--%>
<%--<jsp:include page="../includes/end.jsp"></jsp:include>--%>

<%--<script>--%>
<%--    const addIngredientButton = document.querySelector(".add-ingredient")--%>
<%--    const ingredientForm = document.querySelector(".inner-form")--%>
<%--    // const ingredientFormContainer = document.querySelector(".inner-form-container")--%>
<%--    const ingredientFormHideButton = document.querySelector(".inner-form-hide")--%>
<%--    addIngredientButton.addEventListener("click", (e) => {--%>
<%--        ingredientForm.style.visibility = "visible"--%>
<%--    })--%>
<%--    ingredientFormHideButton.addEventListener("click", (e) => {--%>
<%--        ingredientForm.style.visibility = "hidden"--%>
<%--    })--%>
<%--    ingredientForm.addEventListener("submit", (e) => {--%>
<%--        e.preventDefault()--%>
<%--        const data = new FormData(ingredientForm)--%>
<%--        console.log(e.target)--%>
<%--        for (const [key, value] of data) {--%>
<%--            console.log(key, value)--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>