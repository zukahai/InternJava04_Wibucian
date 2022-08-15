<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../../admin/includes/hd.jsp"></jsp:include>
<jsp:include page="../../admin/includes/header.jsp"></jsp:include>
<jsp:include page="../../admin/includes/sidebar1.jsp"></jsp:include>
<jsp:include page="../../admin/includes/container.jsp"></jsp:include>

<style>
    .col {
        padding-left: 20%;
    }
</style>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header flex-row">
			<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
				<!--begin::Card title-->
				<div class="card-title m-0">
					<h3 class="fw-bold m-0">Tạo yêu cầu xoay ca
					</h3>
				</div>
			</div>
		</div>
		<div class="card-body p-9">
			<!--end::Input group-->
			<div class="table-responsive fs-5 gy-5 border-bottom-dashed pb-10">
				<div class="row row-cols-2 mb-5 px-20 w-75 mx-auto">
					<div class="col">Mã ca làm</div>
					<div class="col">${shift.id}</div>
				</div>
				<div class="row row-cols-2 mb-5 px-20 w-75 mx-auto">
					<div class="col">Ngày làm việc</div>
					<div class="col">
						<fmt:formatDate pattern="dd-MM-yyyy"
										value="${shift.shiftDate}"/>
					</div>
				</div>
				<div class="row row-cols-2 mb-5 px-20 w-75 mx-auto">
					<div class="col">Buổi làm việc</div>
					<div class="col">
						<c:choose>
							<c:when test="${shift.shiftCode eq 1}">Sáng</c:when>
							<c:when test="${shift.shiftCode eq 2}">Trưa</c:when>
							<c:when test="${shift.shiftCode eq 3}">Chiều</c:when>
						</c:choose>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty candidateShiftsToRotate}">
					<h3 class="text-center pt-5">Không có ca làm nào có thể xoay ca</h3>
				</c:when>
				<c:otherwise>
					<form class="shift-rotate-form" method="post">
						<h3 class="pt-5 text-center">Các ca làm việc có thể xoay ca</h3>
						<input type="hidden" name="idShift" value="${shift.id}"/>
						<table class="table table-row-bordered table-striped gy-5 mt-10">
							<thead>
								<tr class="fw-bold fs-6 text-gray-800 row row-cols-4">
									<th class="col text-center"></th>
									<th class="col text-center">Sáng</th>
									<th class="col text-center">Chiều</th>
									<th class="col text-center">Tối</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="day" items="${daysOfWeek}">
									<tr class="row row-cols-4">
										<td class="col d-flex align-items-center justify-content-center fw-bold">
												${weekDayMapping[day.value]}
										</td>
										<c:forEach var="shiftOfDay"
												   items="${shiftsOfDay}">
											<td class="col d-flex flex-column gap-5 align-items-center justify-content-center">
												<c:forEach var="shiftNo"
														   items="${[0, 1]}">
													<c:set var="shiftExchange"
														   value="${candidateShiftsToRotate[day][shiftOfDay][shiftNo]}"/>
													<c:choose>
														<c:when test="${empty shiftExchange}">
															<c:set var="shiftExchangeId"
																   value=""/>
															<c:set var="employeeChangeId"
																   value=""/>
														</c:when>
														<c:otherwise>
															<c:set var="shiftExchangeId"
																   value="${shiftExchange.id}"/>
															<c:set var="employeeChangeId"
																   value="${shiftExchange.employee.id}"/>
														</c:otherwise>
													</c:choose>
													<c:if test="${not empty shiftExchange}">
														<div class="form-check form-check-custom form-check-success form-check-solid">
															<input class="form-check-input shift-exchange-checkbox"
																   name="idShiftExchange"
																   value="${shiftExchangeId}"
																   type="radio"
															/>
															<span class="mx-3">${employeeChangeId}</span>
														</div>
													</c:if>
												</c:forEach>
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<button class="btn btn-secondary btn-lg disabled d-block mx-auto my-5 submit-button">
							Xác nhận
						</button>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../../admin/includes/footer.jsp"></jsp:include>
<jsp:include page="../../admin/includes/end.jsp"></jsp:include>

<script>
    const inputElements = document.querySelectorAll(".shift-exchange-checkbox")
    const formElement = document.querySelector(".shift-rotate-form")
    const submitButton = document.querySelector(".submit-button")

    inputElements.forEach(input => input.addEventListener("change", (e) => {
        submitButton.classList.remove("btn-secondary", "disabled")
        submitButton.classList.add("btn-primary", "active")
    }))
</script>

<style>
    th {
        padding: 0;

    }

    td {
        text-align: center;
    }
</style>

