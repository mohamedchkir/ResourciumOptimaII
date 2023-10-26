<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
  <title>Login</title>
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
              <h4 class="mb-sm-0 font-size-18">Equipment List</h4>

            </div>
          </div>
        </div>
        <!-- end page title -->

        <!-- end row -->

        <div class="table-responsive mb-4">
          <table class="table align-middle datatable dt-responsive table-check nowrap" style="border-collapse: collapse; border-spacing: 0 8px; width: 100%;">
            <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Status</th>
              <th scope="col">Email</th>
              <th scope="col">Buy Date</th>
              <th style="width: 80px; min-width: 80px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${equipments}" var="equipment">

              <tr>
              <td>${equipment.name}</td>
              <td><span class="badge badge-soft-dark">${equipment.status}</span></td>
              <td>${equipment.buy_date}</td>
              <td><span class="badge badge-soft-primary">${equipment.type}</span></td>
              <td>
                <div class="d-flex gap-2">
                  <form action="${pageContext.request.contextPath}/deleteEquipments" method="get">
                    <input type="hidden" name="EquipmentId" value="${equipment.id}" />
                  <button type="submit" class="btn btn-soft-danger waves-effect waves-light"><i class="bx bx-block font-size-16 align-middle"></i></button>
                  </form>
                    <button type="button"   class="btn btn-soft-warning waves-effect waves-light"><i class="bx bx-pencil font-size-16 align-middle"></i></button>

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

<jsp:include page="../components/rightSidebar.jsp" />

<jsp:include page="../components/scripts.jsp" />
<!-- pace js -->

</body>
</html>



