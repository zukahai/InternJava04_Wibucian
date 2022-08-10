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
		<c:choose>
			<c:when test="${isInShiftRequestTime eq true}">
				<!--begin::Card header-->
				<div class="card-header flex-row">
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
						<!--begin::Card title-->
						<div class="card-title m-0">
							<h3 class="fw-bold m-0">Dach sách đăng ký lịch làm việc</h3>
						</div>
						<div class="card-title mt-2">
							<p class="fst-italic m-0 fw-bold">Thời gian: từ ${weekStart}
								đến ${weekEnd} </p>
						</div>
					</div>
					<div class="d-flex justify-content-center align-items-lg-start flex-column">
						<div class="d-flex justify-content-center align-items-center pb-2">
							<div class="h-20px w-20px bg-primary"></div>
							<div class="px-4">Đăng ký bình thường</div>
						</div>
						<div class="d-flex justify-content-center align-items-center pt-2">
							<div class="h-20px w-20px bg-warning"></div>
							<div class="px-4">Đăng ký dự bị</div>
						</div>
					</div>
				</div>
				<div class="card-body p-9">
					<!--end::Input group-->
					<div class="table-responsive">
						<table class="table table-row-bordered table-striped gy-5">
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
										<td class="col d-flex align-items-center justify-content-center">${day}</td>
										<c:forEach var="shiftOfDay"
												   items="${shiftsOfDay}">
											<td class="col text-center d-flex flex-column align-items-center gap-5">
												<c:forEach var="shift"
														   items="${shiftRequestsForNextWeek[day][shiftOfDay]}">
													<c:choose>
														<c:when test="${shift.overtimeRequest eq false}">
															<c:set var="textColor"
																   value="text-primary"/>
														</c:when>
														<c:otherwise>
															<c:set var="textColor"
																   value="text-warning"/>
														</c:otherwise>
													</c:choose>
													<div class="${textColor}">${shift.employee.id}</div>
												</c:forEach>
												<c:forEach var="workPlan"
														   items="${workPlansForNextWeek[day][shiftOfDay]}">
													<c:if test="${workPlan.work eq false}">
														<div class="text-danger">Nghỉ</div>
													</c:if>
												</c:forEach>
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!--end::Card body-->
			</c:when>
			<c:otherwise>
				<div class="card-body p-9 fs-2 text-center">Xin lỗi, hiện tại không phải
					là thời gian
					đăng ký ca làm việc.
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

