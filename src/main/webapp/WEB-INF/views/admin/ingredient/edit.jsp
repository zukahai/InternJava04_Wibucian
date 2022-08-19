<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<div class="content flex-column-fluid" id="kt_content">
	
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<div class="card-header cursor-pointer">
			<div class="card-title m-0">
				<h3 class="fw-bold m-0">Sửa Nguyên Liệu</h3>
			</div>
			<a href="admin/ingredient/" class="btn btn-primary align-self-center">Danh
				sách nguyên liệu
			</a
			>
		</div>
		<div class="card-body p-9">
			<form action="/admin/ingredient/edit" method="post">
				<div class="form-floating my-5">
					<input type="text" class="form-control" id="ingredientId"
						   name="ingredientId" value="${xemdanhsach.id}" readonly/>
					<label for="ingredientName">ID</label>
				</div>
				<div class="form-floating my-5">
					<input type="text" class="form-control" id="ingredientName"
						   name="ingredientName" value="${xemdanhsach.ingredientName}" required/>
					<label for="ingredientName">Tên nguyên liệu</label>
				</div>
				<c:set var="expireDay">
					<fmt:formatDate pattern="yyyy-MM-dd"
									value="${xemdanhsach.expiryIngredient}" />
				</c:set>
				<div class="form-floating my-5">
					<input type="date" class="form-control" id="expiryIngredient"
						   name="expiryIngredient" value="${expireDay}" required/>
					<label for="expiryIngredient">Hạn sử dụng</label>
				</div>
				<div class="form-floating my-5">
					<input type="text" class="form-control" id="price" name="price"
						   value="${xemdanhsach.price}" required/>
					<label for="price">Giá</label>
				</div>
				<div class="form-floating my-5">
					<input type="text" class="form-control" id="origin" name="origin"
						   value="${xemdanhsach.origin}" required/>
					<label for="origin">Nguồn gốc</label>
				</div>
				<div class="form-floating my-5">
					<input type="text" class="form-control" id="unit" name="unit"
						   value="${xemdanhsach.unit}" required/>
					<label for="unit">Đơn vị</label>
				</div>
				
				
				<div class="text-center my-5">
					<button class="btn btn-primary" type="submit">Sửa</button>
				</div>
			</form>
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>