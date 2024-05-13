package exception;

import models.House;
import models.Room;
import models.Villa;
import services.impl.FacilityService;

import java.io.IOException;
import java.util.LinkedHashMap;

public class FacilityExceptionsUtils extends Exception {
    public FacilityExceptionsUtils(String message) {
        super(message);
    }

    public static boolean codeVillaCheck(String codeService) {
        try {
            LinkedHashMap<Villa, Integer> villaList = FacilityService.readVilla();
            String regex = "^SVVL-[0-9]{4}$";
            if (!codeService.matches(regex)) {
                throw new FacilityExceptionUtils("Không đúng định dạng, mời nhập lại");
            }
            for (Villa key : villaList.keySet()) {
                if (key.equals(codeService)) {
                    throw new FacilityExceptionUtils("Mã dịch vụ đã tồn tại, mời nhập lại!");
                }
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Không đọc được file!");
            return false;
        }
    }

    public static boolean codeHouseCheck(String codeService) {
        try {
            LinkedHashMap<House, Integer> houseList = FacilityService.readHouse();
            String regex = "^SVHO-[0-9]{4}$";
            if (!codeService.matches(regex)) {
                throw new FacilityExceptionUtils("Không đúng định dạng, mời nhập lại");
            }
            for (House key : houseList.keySet()) {
                if (key.equals(codeService)) {
                    throw new FacilityExceptionUtils("Mã dịch vụ đã tồn tại, mời nhập lại!");
                }
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Không đọc được file!");

        }
        return false;
    }

    public static boolean codeRoomCheck(String codeService) {
        try {
            LinkedHashMap<Room, Integer> roomList = FacilityService.readRoom();
            String regex = "^SVRO-[0-9]{4}$";
            if (!codeService.matches(regex)) {
                throw new FacilityExceptionUtils("Không đúng định dạng, mời nhập lại");
            }
            for (Room key : roomList.keySet()) {
                if (key.equals(codeService)) {
                    throw new FacilityExceptionUtils("Mã dịch vụ đã tồn tại, mời nhập lại!");
                }
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Không đọc được file!");
            return false;
        }
    }

    public static boolean areaUseCheck(Double areaUse) {
        try {
            if (areaUse < 30) {
                throw new FacilityExceptionUtils("Diện tích sử dụng phải lớn hớn 30m2, mời nhập lại!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean nameServiceCheck(String name) {

        try {
            String regex = "^([A-ZĐ][a-záàảãạăâắằấầặẵẫêậẩéèẻẽẹếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+[ ])+[a-záàảãạăâắằấầặẵẫậéèẻẽẹếềểễệóòêỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ0-9]$";

            if (!name.matches(regex)) {
                throw new FacilityExceptionUtils("Tên dịch vụ phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường. Vui lòng nhập đúng định dạng!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean swimmingPoolAareaCheck(Double swimmingPoolAarea) {
        try {
            if (swimmingPoolAarea < 30) {
                throw new FacilityExceptionUtils("Diện tích sử dụng phải lớn hớn 30m2, mời nhập lại!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean rentalCoatCheck(Double rentalCost) {
        try {
            if (rentalCost < 0) {
                throw new FacilityExceptionUtils("Chi phí thuê phải là số dương, mời nhập lại");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean maxPersonCheck(int maxPerson) {
        try {
            if (maxPerson < 0 && maxPerson > 20) {
                throw new FacilityExceptionUtils("Số lượng người tối đa phải phải lớn hơn 0 và nhỏ hơn 20, mời nhập lại");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean numberOfFloorsCheck(int numberOfFloors) {
        try {
            if (numberOfFloors < 0) {
                throw new FacilityExceptionUtils("Số tầng phải là số nguyên dương, mời nhập lại!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean roomStandardCheck(String roomStandard) {
        try {
            String regex = "^([A-ZĐ][a-záàảãạăâắằấầặẵẫêậẩéèẻẽẹếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+[ ])+[a-záàảãạăâắằấầặẵẫậéèẻẽẹếềểễệóòêỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+$";

            if (!roomStandard.matches(regex)) {
                throw new FacilityExceptionUtils("Tiêu chuẩn phòng phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường. Vui lòng nhập đúng định dạng!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean typeOfRentalCheck(String typeOfRental) {
        try {
            String regex = "^([A-ZĐ][a-záàảãạăâắằấầặẵẫêậẩéèẻẽẹếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+[ ])+[a-záàảãạăâắằấầặẵẫậéèẻẽẹếềểễệóòêỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+$";

            if (!typeOfRental.matches(regex)) {
                throw new FacilityExceptionUtils("Kiểu thuê phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường. Vui lòng nhập đúng định dạng!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean freeServiceCheck(String freeService) {
        try {
            String regex = "^([A-ZĐ][a-záàảãạăâắằấầặẵẫêậẩéèẻẽẹếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+[ ])+[a-záàảãạăâắằấầặẵẫậéèẻẽẹếềểễệóòêỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+$";

            if (!freeService.matches(regex)) {
                throw new FacilityExceptionUtils("Dịch vụ miễn phí đi kèm phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường. Vui lòng nhập đúng định dạng!");
            }
            return true;
        } catch (FacilityExceptionUtils e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    private static class FacilityExceptionUtils extends Throwable {
        public FacilityExceptionUtils(String s) {
        }
    }
}

