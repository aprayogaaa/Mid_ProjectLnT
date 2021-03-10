import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
public class Main {
	
	Scanner scan = new Scanner (System.in);
	Vector <Karyawan> vKaryawan = new Vector<>();
	Random rand = new Random ();
	
	int countManager=0;
	int countAdmin=0;
	int countSupervisor=0;
	
	public Main() {
		mainMenu();
	}
	
	void mainMenu() {
		int menu = 0;
		do {
			System.out.println("Data Karyawan PT. Mentol");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.print("Menu : ");
			menu = icatch();
			
			switch (menu) {
			case 1:
				insertData();
				break;
			case 2:
				viewData();
				break;
			case 3:
				updateData();
				break;
			case 4:
				deleteData();
				break;
			case 5:
				System.exit(0);
			}
			
		} while (menu !=5);
	}

	private void insertData() {
		
		String nama = "", kelamin = "", jabatan = "", id = "";
		double gaji = 0;
		
		do {
			System.out.print("Input Nama Karyawan [>=3] : ");
			nama = scan.nextLine();
			if (!(nama.length() >=3)) {
				System.out.println("Harus tiga karater atau lebih!");
			}
		} while (!(nama.length() >=3));
		
		do {
			System.out.print("Input Jenis Kelamin [Laki-Laki | Perempuan] : ");
			kelamin = scan.nextLine();
			if (!kelamin.equals("Laki-Laki") && !kelamin.equals("Perempuan")) {
				System.out.println("Harus Laki-Laki atau Perempuan!");
			}
		} while (!kelamin.equals("Laki-Laki") && !kelamin.equals("Perempuan"));
		
		do {
			System.out.print("Input Jabatan [Manager | Supervisor | Admin] : ");
			jabatan = scan.nextLine();
			if (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin")) {
				System.out.println("Harus Manager, Supervisor, atau Admin!");
			}
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if (jabatan.equals("Manager")) {
			gaji = 8000000;
		}else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
		}else {
			gaji = 4000000;
		}
		
		id = id + ((char)makeRandom('A', 'Z')) + ((char)makeRandom('A', 'Z')) + "-"+ (makeRandom(0, 9)) + (makeRandom (0, 9)) + (makeRandom(0, 9));
		
		if (jabatan.equals("Manager")) {
			vKaryawan.add(new Manager (nama, kelamin, jabatan, id, gaji));
			++countManager;
		}else if (jabatan.equals("Supervisor")){
			vKaryawan.add(new Admin (nama, kelamin, jabatan, id, gaji));
			++countSupervisor;
		}else if (jabatan.equals("Admin")) {
			vKaryawan.add(new Admin (nama, kelamin, jabatan, id, gaji));
			++countAdmin;
		}
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + id);
		
		if (countManager %3==1 && countManager > 3) {
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
//			int counter = 0;
			for (int i = 0; i < countManager-1; i++) {
				if (vKaryawan.get(i) instanceof Manager) {
					gaji = vKaryawan.get(i).getGaji()*(1.1);
					vKaryawan.get(i).setGaji(gaji);
					System.out.print(vKaryawan.get(i).getId());
					System.out.print(", ");
					System.out.println();
//					counter++;
//					if (counter == countManager) {
//						System.out.println(", ");
//					}else {
//						System.out.println(" ");
//					}
				}
			}
		}else if (countSupervisor %3==1 && countSupervisor > 3) {
			System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
			System.out.println();
//			int counter2 = 0;
			for (int i = 0; i < vKaryawan.size() - 1; i++) {
				if (vKaryawan.get(i) instanceof Supervisor) {
					gaji = vKaryawan.get(i).getGaji()*(1.075);
					vKaryawan.get(i).setGaji(gaji);
					System.out.print(vKaryawan.get(i).getId());
					System.out.print(", ");
					System.out.println();
//					counter2++;
//					if (counter2 == countSupervisor - 2) {
//						System.out.println(", ");
//					} else {
//						System.out.println(" ");
//					}
				}
			}
		}else if (countAdmin %3==1 && countAdmin > 3) {
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
			System.out.println();
//			int counter3 = 0;
			for (int i = 0; i < countAdmin-1; i++) {
				if (vKaryawan.get(i) instanceof Admin) {
					gaji = vKaryawan.get(i).getGaji()*(1.05);
					vKaryawan.get(i).setGaji(gaji);
					System.out.println(vKaryawan.get(i).getId());
					System.out.print(", ");
					System.out.println();
//					counter3++;
//					if (counter3 == countAdmin - 2) {
//						System.out.println(", ");
//					}else {
//						System.out.println(" ");
//					}
				}
			}
		}
	}

	private void viewData() {
	
	if (vKaryawan.isEmpty()) {
		System.out.println("Data tidak ditemukan!");
	}else {
		for (int i = 0; i < vKaryawan.size(); i++) {
			for (int j = 0; j < vKaryawan.size()-1-i; j++) {
				if (vKaryawan.get(j).getNama().compareTo(vKaryawan.get(j+1).getNama()) >0) {
					Karyawan temp = vKaryawan.get(j);
					vKaryawan.set(j, vKaryawan.get(j+1));
					vKaryawan.set(j+1, temp);
				}
			}
		}
		
		System.out.println("|------------------------------------------------------------------------------------------------|");
		System.out.println("| No. \t| Kode Karyawan \t| Nama Karyawan    \t| Jenis Kelamin | Jabatan | Gaji Karyawan|");
		System.out.println("|------------------------------------------------------------------------------------------------|");
		for (int i = 0; i < vKaryawan.size(); i++) {
			System.out.println("| " + (i + 1) + "\t|" + vKaryawan.get(i).getId() + "\t\t\t|" + vKaryawan.get(i).getNama() + "\t\t\t|" + vKaryawan.get(i).getKelamin() + "\t|" + vKaryawan.get(i).getJabatan() + "|" + (int)vKaryawan.get(i).getGaji() + "|");
			}
		}	
	}

	private void updateData() {
	
	if (vKaryawan.isEmpty()) {
		System.out.println("Data tidak ditemukan!");
	}else {
		System.out.println("|------------------------------------------------------------------------------------------------|");
		System.out.println("| No. \t| Kode Karyawan \t| Nama Karyawan    \t| Jenis Kelamin | Jabatan | Gaji Karyawan|");
		System.out.println("|------------------------------------------------------------------------------------------------|");
		for (int i = 0; i < vKaryawan.size(); i++) {
			System.out.println("| " + (i + 1) + "\t|" + vKaryawan.get(i).getId() + "\t\t\t|" + vKaryawan.get(i).getNama() + "\t\t\t|" + vKaryawan.get(i).getKelamin() + "\t|" + vKaryawan.get(i).getJabatan() + "|" + (int)vKaryawan.get(i).getGaji() + "|");
		}
		
		Integer update=0;
		do {
			System.out.print("Pilih No. yang ingin diperbarui [1.." + vKaryawan.size() + "] : ");
			update=icatch();
		} while (update < 1 || update > vKaryawan.size());
		update--;
		
		String nama = "", kelamin = "", jabatan = "", id = "";
		double gaji = 0;
		
		do {
			System.out.print("Input Nama Karyawan [>=3] : ");
			nama = scan.nextLine();
			if (!(nama.length() >=3)) {
				System.out.println("Harus tiga karater atau lebih!");
			}
		} while (!(nama.length() >=3));
		
		do {
			System.out.print("Input Jenis Kelamin [Laki-Laki | Perempuan] : ");
			kelamin = scan.nextLine();
			if (!kelamin.equals("Laki-Laki") && !kelamin.equals("Perempuan")) {
				System.out.println("Harus Laki-Laki atau Perempuan!");
			}
		} while (!kelamin.equals("Laki-Laki") && !kelamin.equals("Perempuan"));
		
		do {
			System.out.print("Input Jabatan [Manager | Supervisor | Admin] : ");
			jabatan = scan.nextLine();
			if (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin")) {
				System.out.println("Harus Manager, Supervisor, atau Admin!");
			}
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if (jabatan.equals("Manager")) {
			gaji = 8000000;
		}else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
		}else {
			gaji = 4000000;
		}
		
		id = id + ((char)makeRandom('A', 'Z')) + ((char)makeRandom('A', 'Z')) + "-"+ (makeRandom(0, 9)) + (makeRandom (0, 9)) + (makeRandom(0, 9));

		System.out.println("Berhasil memperbarui karyawan dengan id " + id);
		System.out.println();
		vKaryawan.set(update, new Karyawan(nama, kelamin, jabatan, id, gaji));
		}	
	}

	private void deleteData() {
	
	if (vKaryawan.isEmpty()) {
		System.out.println("Data tidak ditemukan!");
	}else {
		System.out.println("|------------------------------------------------------------------------------------------------|");
		System.out.println("| No. \t| Kode Karyawan \t| Nama Karyawan    \t| Jenis Kelamin | Jabatan | Gaji Karyawan|");
		System.out.println("|------------------------------------------------------------------------------------------------|");
		for (int i = 0; i < vKaryawan.size(); i++) {
			System.out.println("| " + (i + 1) + "\t|" + vKaryawan.get(i).getId() + "\t\t\t|" + vKaryawan.get(i).getNama() + "\t\t\t|" + vKaryawan.get(i).getKelamin() + "\t|" + vKaryawan.get(i).getJabatan() + "|" + (int)vKaryawan.get(i).getGaji() + "|");
		}
		
		Integer delete = 0;
		do {
			System.out.print("Pilih No. yang ingin dihapus [1.." + vKaryawan.size() + "] : ");
			delete = icatch();
		} while (delete < 1 || delete > vKaryawan.size());
		
		if (delete == -1) {
			return;
		}else {
			delete--;
		}

		vKaryawan.remove(delete.intValue());
		System.out.println("Berhasil menghapus data karyawan");
		System.out.println();
		}	
	}

	int icatch() {
		int tcatch = -1;
		try {
			tcatch = scan.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		scan.nextLine();
		return tcatch;
	}
	
	int makeRandom (int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
	
	public static void main(String[] args) {
		new Main();

	}

}
