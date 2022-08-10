<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header flex-row">
			<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
				<!--begin::Card title-->
				<div class="card-title m-0">
					<h3 class="fw-bold m-0">Dach sách yêu cầu xoay ca đang chờ
						được phê duyệt
					</h3>
				</div>
				<div class="card-title mt-2">
					<p class="fst-italic m-0 fw-bold">Thời gian: từ ${weekStart}
						đến ${weekEnd} </p>
				</div>
			</div>
		</div>
		<div class="card-body p-9">
			<!--end::Input group-->
			<div class="table-responsive">
				<table class="table table-row-bordered table-striped gy-5">
					<thead>
						<tr class="fw-bold fs-6 text-gray-800 row row-cols-6">
							<%--									<th class="col text-center">Mã xoay ca</th>--%>
							<th class="col col-1 text-center">
								STT
							</th>
							<th class="col text-center">
								Mã nhân viên 1
							</th>
							<th class="col text-center">
								Mã ca làm 1
							</th>
							<th class="col text-center">Mã nhân viên 2</th>
							<th class="col text-center">Mã ca làm 2</th>
							<th class="col text-center col-3">Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="shiftRotate" items="${shiftRotatesToBeApproved}"
								   varStatus="loop">
							<tr class="fw-bold fs-6 text-gray-800 row row-cols-6">
								<th class="col col-1 d-flex align-items-center justify-content-center">
										${loop.index + 1}
								</th>
								<th class="col col-2 d-flex align-items-center justify-content-center">
										${shiftRotate.employeeCreate.id}
								</th>
								<th class="col col-2 d-flex align-items-center justify-content-center">
										${shiftRotate.shift.id}
								</th>
								<th class="col col-2 d-flex align-items-center justify-content-center">
										${shiftRotate.employeeChange.id}
								</th>
								<th class="col col-2 d-flex align-items-center justify-content-center">
										${shiftRotate.shiftExchange.id}
								</th>
								<th class="col col-3 text-center">
									<button class="btn btn-primary action-btn"
											shiftRotateId="${shiftRotate.id}"
											action="true">
										Chấp nhận
									</button>
									<button class="btn btn-danger action-btn"
											shiftRotateId="${shiftRotate.id}"
											action="false">
										Từ chối
									</button>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<script>
    const actionButtons = document.querySelectorAll(".action-btn")

    actionButtons.forEach(button => {
        button.addEventListener("click", async (e) => {
            const shiftRotateId = button.getAttribute("shiftrotateid")
			const action = button.getAttribute("action")
			const {data, error} = await request(shiftRotateId, action)
			if (error) {
                window.alert(error)
				return
			}
            window.location.reload()
        })
    })

    const request = async (shiftRotateId, action) => {
        const response = await fetch("${contextPath}/admin/shiftRotate/" + shiftRotateId,
                                     {
                                         method: "PATCH",
                                         body: new URLSearchParams({
                                                                       shiftRotateId,
                                                                       approve: action
                                                                   })
                                     })
        const responseData = await response.json()
        // status khác 200 nghĩa là có lỗi
        if (response.status !== 200) {
            return {
                error: "Không thể thực hiện"
            }
        }
        return {
            data: "OK"
        }
    }
</script>

