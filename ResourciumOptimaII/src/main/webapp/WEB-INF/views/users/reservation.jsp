<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <title>Reservations</title>
    <jsp:include page="../components/head.jsp" />
</head>
<body>
<div id="layout-wrapper">
    <jsp:include page="../components/navbar.jsp" />
    <jsp:include page="../components/sidebar.jsp" />

    <div class="main-content">
        <div class="page-content">
            <div class="container-fluid">
                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                            <h4 class="mb-sm-0 font-size-18">Reservations List</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
                <div class="d-flex flex-wrap align-items-center justify-content-end ">
                    <div class="col-md-6">
                        <div class="d-flex flex-wrap align-items-center justify-content-end gap-2 mb-3">
                            <div>
                                <a href="#" class="btn btn-light"><i class="bx bx-plus me-1" data-bs-toggle="modal" data-bs-target="#myModal" aria-hidden="true"></i> Add New</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
                <div class="table-responsive mb-4">
                    <table class="table align-middle datatable dt-responsive table-check nowrap" style="border-collapse: collapse; border-spacing: 0 8px; width: 100%;">
                        <thead>
                        <tr>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">User</th>
                            <th scope="col">Equipment</th>
                            <th style="width: 80px; min-width: 80px;">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reservations}" var="reservation">

                            <fmt:formatDate value="${reservation.start_date}" pattern="yyyy-MM-dd" var="start_date" />
                            <fmt:formatDate value="${reservation.end_date}" pattern="yyyy-MM-dd" var="end_date" />

                            <tr>
                                <td>${start_date}</td>
                                <td>${end_date}</td>
                                <td>${reservation.user.name}</td>
                                <td>${reservation.equipment.name}</td>
                                <td>
                                    <div class="d-flex gap-2">
                                        <form action="deleteReservation" method="get">
                                            <input type="hidden" name="reservationId" value="${reservation.id}" />
                                            <button type="submit" class="btn btn-soft-danger waves-effect waves-light"><i class="bx bx-block font-size-16 align-middle"></i></button>
                                        </form>
                                        <button type="button" class="btn btn-soft-warning waves-effect waves-light"><i class="bx bx-pencil font-size-16 align-middle"></i></button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- end table -->
                </div>
                <!-- end table responsive -->
            </div> <!-- container-fluid -->
        </div>
        <!-- End Page-content -->
    </div>
</div>

<!-- sample modal content -->
<div id="myModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="needs-validation" method="post" action="reservations" novalidate>
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">Add Reservation</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="flex-column">
                        <div class="col-md-12">
                            <div class="form-group mb-3">
                                <label class="form-label" for="validationCustom04">Equipment</label>
                                <select required class="form-control form-select" name="equipment" id="validationCustom04">
                                    <option value="">Equipment</option>
                                    <c:forEach var="equipment" items="${equipments}">
                                        <option value="${equipment.id}">${equipment.name}</option>
                                    </c:forEach>
                                </select>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                                <div class="invalid-feedback">
                                    Please provide a valid equipment.
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="mb-3">
                                <label class="form-label" for="validationCustom02">Start Date</label>
                                <input type="date" class="form-control" id="validationCustom02" name="start_date" required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                                <div class="invalid-feedback">
                                    Please provide a valid date.
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <input type="hidden" name="user" value="${userId}" />
                        </div>

                        <div class="col-md-12">
                            <div class="mb-3">
                                <label class="form-label" for="validationCustom03">End Date</label>
                                <input type="date" class="form-control" id="validationCustom03" name="end_date" required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                                <div class="invalid-feedback">
                                    Please provide a valid date.
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary waves-effect waves-light">Save</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../components/rightSidebar.jsp" />
<jsp:include page="../components/scripts.jsp" />
<!-- validation init -->
<script src="${pageContext.request.contextPath}/resources/js/pages/validation.init.js"></script>
<!-- pace js -->
</body>
</html>
