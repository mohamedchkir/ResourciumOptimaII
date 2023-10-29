<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
  <title>Equipment List</title>
  <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
  <jsp:include page="../components/head.jsp" />
</head>

<body>
<div id="layout-wrapper">
  <jsp:include page="../components/navbar.jsp" />
  <jsp:include page="../components/sidebar.jsp" />

  <div class="main-content">
    <div class="page-content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="page-title-box d-sm-flex align-items-center justify-content-between">
              <h4 class="mb-sm-0 font-size-18">Equipment List</h4>
            </div>
          </div>
        </div>

        <div class="d-flex flex-wrap align-items-center justify-content-end">
          <div class="col-md-6">
            <div class="d-flex flex-wrap align-items-center justify-content-end gap-2 mb-3">
              <div>
                <a href="#" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#equipmentModal">
                  <i class="bx bx-plus me-1" aria-hidden="true"></i> Add New
                </a>
              </div>
            </div>
          </div>
        </div>

        <div class="table-responsive mb-4">
          <table class="table align-middle datatable dt-responsive table-check nowrap"
                 style="border-collapse: collapse; border-spacing: 0 8px; width: 100%;">
            <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Status</th>
              <th scope="col">Buy Date</th>
              <th scope="col">Type</th>
              <th style="width: 80px; min-width: 80px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${equipments}" var="equipment">
              <fmt:formatDate value="${equipment.buy_date}" pattern="yyyy-MM-dd" var="formattedDate" />
              <tr>
                <td>${equipment.name}</td>
                <td><span class="badge badge-soft-dark">${equipment.status}</span></td>
                <td>${formattedDate}</td>
                <td><span class="badge badge-soft-primary">${equipment.type}</span></td>
                <td>
                  <div class="d-flex gap-2">
                    <form action="deleteEquipment" method="get">
                      <input type="hidden" name="EquipmentId" value="${equipment.id}" />
                      <button type="submit" class="btn btn-soft-danger waves-effect waves-light"><i class="bx bx-block font-size-16 align-middle"></i></button>
                    </form>
                    <button type="button" class="btn btn-soft-warning edit-button"
                            data-equipment-id="${equipment.id}"
                            data-equipment-name="${equipment.name}"
                            data-equipment-status="${equipment.status}"
                            data-equipment-type="${equipment.type}"
                            data-equipment-date="${formattedDate}">
                      <i class="bx bx-pencil font-size-16 align-middle"></i>
                    </button>
                  </div>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div> <!-- container-fluid -->
    </div>
  </div>
</div>

<!-- Equipment Modal -->
<div id="equipmentModal" class="modal fade" tabindex="-1" aria-labelledby="equipmentModalLabel" aria-hidden="true">
  <form class="needs-validation" method="post" action="" id="equipmentForm" novalidate>
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="equipmentModalLabel">Equipment Details</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="col-md-12">
            <input type="hidden" id="equipmentId" name="id">
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label class="form-label" for="equipmentName">Name</label>
              <input type="text" class="form-control" id="equipmentName" placeholder="Name" name="name" required>
              <div class="valid-feedback">
                Looks good!
              </div>
              <div class="invalid-feedback">
                Please provide a valid name.
              </div>
            </div>
          </div>
          <div class="col-md-12">
            <div class="form-group mb-3">
              <label class="form-label" for="equipmentStatus">Status</label>
              <select required class="form-control form-select" id="equipmentStatus" name="status">
                <option value="AVAILABLE">Available</option>
                <option value="IN_USE">In USE</option>
                <option value="UNDER_MAINTENANCE">Under Maintenance</option>
                <option value="OUT_OF_SERVICE">Out Of Service</option>
              </select>
            </div>
            <div class="valid-feedback">
              Looks good!
            </div>
            <div class="invalid-feedback">
              Please Select a Status.
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label class="form-label" for="equipmentType">Type</label>
              <input type="text" class="form-control" id="equipmentType" placeholder="Type" name="type" required>
              <div class="valid-feedback">
                Looks good!
              </div>
              <div class="invalid-feedback">
                Please provide a valid type.
              </div>
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="equipmentDate" class="form-label">Hiring Date</label>
              <input type="date" class="form-control" id="equipmentDate" name="date" required>
              <div class="invalid-feedback">
                Please Enter Buy date
              </div>
              <div class="valid-feedback">
                Looks good!
              </div>
              <div class="invalid-feedback">
                Please Select a date.
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary" id="saveButton">Save Changes</button>
        </div>
      </div>
    </div>
  </form>
</div>

<script>
  // Function to open the equipment modal and set its action and method
  function openEquipmentModal(action, method, id, name, status, type, date) {
    $('#equipmentForm').attr('action', action).attr('method', method);
    $('#equipmentId').val(id);
    $('#equipmentName').val(name);
    $('#equipmentStatus').val(status);
    $('#equipmentType').val(type);
    $('#equipmentDate').val(date);
    $('#saveButton').text(action === 'equipments' ? 'Add Equipment' : 'Save Changes');
    $('#equipmentModalLabel').text(action === 'equipments' ? 'Add Equipment' : 'Edit Equipment');
    $('#equipmentModal').modal('show');
  }

  // Add event listeners to trigger the equipment modal for add and edit
  $(document).ready(function () {
    $('#equipmentModal').on('hidden.bs.modal', function () {
      // Reset the form when the modal is closed
      $('#equipmentForm')[0].reset();
    });

    $('.edit-button').click(function () {
      var id = $(this).data('equipment-id');
      var name = $(this).data('equipment-name');
      var status = $(this).data('equipment-status');
      var type = $(this).data('equipment-type');
      var date = $(this).data('equipment-date');
      openEquipmentModal('updateEquipment', 'post', id, name, status, type, date);
    });

    $('.btn-light').click(function () {
      openEquipmentModal('equipments', 'post', '', '', '', '', '');
    });
  });
</script>

<jsp:include page="../components/rightSidebar.jsp" />
<jsp:include page="../components/scripts.jsp" />
</body>
</html>
