import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class LegoStore {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

///////////////////////////////////////////////////////////////////////////
		
		// �� ������ ������ ���� ����Ʈ
		// {{���� ��ȣ}, {���� ����ġ}, {������}}
		// ���� ����ġ�� �ʱⰪ�� 0, �������� �ʱⰪ�� -1�̴�.
		int[][] Vlist = { { 1, 2, 3, 4, 5, 6 }, { 70, 50, 40, 0, 0, 0 }, { 0, 0, 0, -1, -1, -1 } };

		// �� ������ ������ ����Ʈ, (���� 4,5,6���� ����ִ� �����̴�.)
		String[] VlistName = { "���� �޴��� ���ִ� ����", "���� ��Ʈ����", "������� ���� ��¥", "", "", "" };

		// (����������)�ð� ��� �� ������ ������ ��ġ(1~10)
		// {12����,34����,567����}�� �� �׸񸶴� ���� (���� {{1�� ������ �ð� ���� ������}, {2�� ������...},...}
		int[][] Classcnt = { { 5, 10, 10 }, { 7, 5, 8 }, { 5, 5, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		// �󺹹� ������ ���� ����
		int finish = 0;
		
///////////////////////////////////////////////////////////////////////////
		
		// 1�� ���� ȭ��_menu1
		System.out.println("=========���� ȿ������ ���� �湮 �ð� �븦 �м��մϴ�=========");
		System.out.println("*���� ���� �켱 ����: 0. �ð� ��\n1. ���� �޴��� ���ִ� ����\t2. ���� ��Ʈ����\t3. ������� ���� ��¥"); // �ʱ� ���� ��, �׻� ����
		System.out.println("=================================================");

///////////////////////////////////////////////////////////////////////////
		
		// �ݺ��� ����
		while (finish == 0) {
			System.out.println("���� �޴� ����: 0. ���� ���� ����\t1.�ٷ� �м�"); // menu1

///////////////////////////////////////////////////////////////////////////
			
			// menu1 ���� ����
			int menu1 = sc.nextInt();

			// menu1 �� ���๮
			// menu1_0.���� ���� ���� �޴� ���� ��,
			if (menu1 == 0) {
				// 2-1�� ���� ȭ��_menu2
				System.out.println("���� ������ �����մϴ�.");
				System.out.println("���� �޴� ����: 1. ���� ����\t2. ���� �߰�\t3. ���� ����");

				// menu2_���� �޴�
				int menu2 = sc.nextInt();

				// menu2 �� ���๮
				switch (menu2) {
				// menu2_1. ���� ���� �޴� ���� ��,
				case 1: {
					System.out.println("������ ������ ��ȣ�� �Է����ּ���:");
					System.out.println("(��, �ð� �� ������ ������ �� �����ϴ�.)"); // �ð� ��� 0�� ���� ����

					// ������ ��� ���� ����
					PrintVLess(VlistName);
					System.out.println("\n");

					// ���� ���� ��ȣ ����
					int del = sc.nextInt();

					// ���� ���� �Լ� ����
					DelV(del, Vlist, VlistName, Classcnt);

					System.out.println("\n���� ������ ������Ʈ �Ǿ����ϴ�.");

					// ���� ���� ���� �ڼ��� ���
					PrintV(Vlist, VlistName, Classcnt);
					break;
				}

				// menu2_2.���� �߰� �޴� ���� ��,
				case 2: {
					System.out.println("�߰��� ���� ���� �Է��ϼ���.(�ִ� 6���� ����, ���� �Ұ���):");
					System.out.println("(���ο� ������ 1~100 ������ ������ ��ġȭ�� �� �־�� �մϴ�.)");
					System.out.println("(�������� �л����� �����湮���� �ݺ���ؾ� �մϴ�.)");
					System.out.println("*6���� �̻� �Է� ��, ���� ����ġ�� ���� ������ ���ŵ˴ϴ�.*");

					// ���ο� ���� �� ����_next�� ����Ͽ��� ������ ���� �Ұ�
					String newVName = sc.next();

					// ����ġ ����
					System.out.println(newVName + "�� ����ġ�� �Է��ϼ���.(1~100 ����):");
					int newVWeight = sc.nextInt();

					// ���� �� ����
					System.out.println("���� ���� �������ּ���.(1~100 ����):");
					System.out.println("(�Է¹��� �� 0���� ����)");
					int Val = sc.nextInt();

					// ������ ��ġ ����
					System.out.println("���� �������� ������ ��ġ1,2,3�� �Է��ϼ���:");
					int[] Cnt = new int[3];
					for (int i = 0; i <= 2; i++) {
						Cnt[i] = sc.nextInt();
					}

					// ��� �迭�� ���ο� ���� ���� �߰�
					AddV(newVWeight, newVName, Val, Cnt, Vlist, VlistName, Classcnt);

					System.out.println("\n���� ������ ������Ʈ �Ǿ����ϴ�.");

					// ���� ���� ���� �ڼ��� ���
					PrintV(Vlist, VlistName, Classcnt);

					break;
				}

				// menu2_3. ���� ���� �޴� ���� ��,
				case 3: {
					System.out.println("������ ���� ��ȣ�� �Է��ϼ���:");

					// ��� ������ ���� ��� ���� ����
					PrintVLess(VlistName);
					System.out.println("\n");

					// ������ ���� ��ȣ ����
					int modi = sc.nextInt();

					// �ش� �������� ���� �ڼ��� ���� ����
					PrintVMore(modi, Vlist, VlistName, Classcnt);

					// ������ ����
					System.out.println("������ ���氪�� �Է��ϼ���(���� �Ұ���):");
					String modiName = sc.next();
					// ������ ���� �Լ�
					Name(modi, modiName, VlistName);

					// ����ġ ����_�Ұ� :: ����->�߰� ������� ����
					System.out.println("����ġ�� ������ �� �����ϴ�. ���� �� �߰� ���");

					// ���� �� ����
					System.out.println("������ ���氪�� �Է��ϼ���.(1~100 ����):");
					System.out.println("(�Է¹��� �� 0���� ����)");
					int modiVal = sc.nextInt();
					// ������ ���� �Լ�
					Value(modi, modiVal, Vlist);

					// ������ ��ġ ����
					System.out.println("������ ��ġ ���氪1,2,3�� �Է��ϼ���.:");
					int[] Cnt2 = new int[3];
					for (int i = 0; i <= 2; i++) {
						Cnt2[i] = sc.nextInt();
					}
					// ������ ��ġ ���� �Լ�
					Connect(modi, Cnt2, Classcnt);

					System.out.println("\n���� ������ ������Ʈ �Ǿ����ϴ�.");

					// ���� ���� ���� �ڼ��� ���
					PrintV(Vlist, VlistName, Classcnt);
					break;
				}
				}// switch�� ����
			} // menu1=0 ���� (menu1=0�� ��쿡���� �׻� while �ݺ��� �ȿ�...==> ������ ��, ó������ �����)

///////////////////////////////////////////////////////////////////////////
			
			// menu1_1. �ٷκм� �޴� ����
			else if (menu1 == 1) {
				System.out.println("���� �ð� �м��� �����մϴ�.");
				// 1~7���� ���� �ð� �� �Ұ����� �ð� �Է� ����
				System.out.println("�ð��� �����ų� �湮�� �Ұ����� �ð����� ���� �Է����ּ���.:");
				int numtime = sc.nextInt(); // �Ұ����� �ð����� ����

				System.out.println("�ð��� �����ų� �湮�� �Ұ����� �ð��븦 �Է����ּ���.:");
				System.out.println("(n �Է� ��, n���� ���� �ð��� �ش�)");

				int[] timeList = { 1, 2, 3, 4, 5, 6, 7 }; // ��� ���ÿ� ���� �迭
				for (int i = 0; i < numtime; i++) {
					// �Ұ����� �ð��븦 1�������� ���� ����
					int noTime = sc.nextInt();

					// �ش� �Ұ����� �ð� ������ timeList �迭���� ����(7���ð� �ԷµǾ����� ���� 7�� 0���� ��ȯ)
					timeList[noTime - 1] = 0;
				} // �Ұ����� �ð��� ���� ��

				// ������ ���� �� ���๮
				// ���� �������� �ٷ��.
				// ���ܺ���) ��� ������ 1~100 ������ ������ �������� �Է¹��� �� ������,
				// ���� ������ �Ұ����ϴ�. ���� ���� ���̽��� �з����ش�.

				// ������ ����ڿ� ���� ���ŵ��� �ʾҴٸ�,
				if (Arrays.asList(Vlist).contains("����")) {
					System.out.println("������ ������ �Է��ϼ���:");
					String Day = sc.nextLine();

					// ���� ������ ��ȣ(=�ε���+1)�� ã������(����ڿ� ���� ����Ǿ��� ���� ������)
					// �ش� �ε��� ���� �ο��� ���� daynum ����
					int daynum = -1; // �ʱⰪ�� �迭 ������ ������� �ʱ� ���� -1�� ����

					for (int i = 0; i <= 5; i++) {
						// ������ �ε��� ã��
						// "����"�� VlistName�� ������� ��ġ�ϸ�...
						if (VlistName[i].equals("����")) {
							daynum = i;
							break;
						}

						// �ε������� ã������, ������ ���Ͽ� ���� �ٸ� �������� �迭 Vlist[2][�ε���]�� �ɾ��ش�.
						// ��, ȭ������ ���
						if (Day.equals("������") || Day.equals("ȭ����")) {

							Vlist[2][daynum] = 60;
						}

						// �������� ���
						else if (Day.equals("������")) {
							Vlist[2][daynum] = 40;
						}

						// ��, �ݿ����� ���
						else if (Day.equals("�����") || Day.equals("�ݿ���")) {
							Vlist[2][daynum] = 40;
						}
					}
				} // ���� ������ ���� ó�� ��

				// �Ϲ� ������ ����...
				// ���� ������ �ƴ� �Ϲ� ������ ��, �������� ���� 0���� �����Ǿ�
				// ����ڷκ��� �Է¹޾ƾ��ϴ� ��츦 ���� �ڵ� ����
				int getVal;
				for (int i=0; i<=5; i++) {
					if (Vlist[2][i] == 0) {//�������� 0�̾ �Է� �޾ƾ� �ϴ� ���
						
						// �ڵ� ��ġ �Է�->���� ���๮ ����
						System.out.printf("%s�� ��ġ�� 1~100 ������ ������ �Է��ϼ���:", VlistName[i]);
						
						// �Է¹��� ��ġ(������)
						getVal = sc.nextInt();
						Vlist[2][i] = getVal; // �������� Vlist�� �������� �����ϴ� �迭�� ����
					}
				}
				// ��� ��� ������ ������ �迭�� �ԷµǾ����Ƿ�, ����Լ�(Calcul) ����
				int answer = Calcul(timeList, Vlist, Classcnt);// ���ϵ� ���� ���� answer�� ����
				System.out.printf("\n���� ȿ������ ���� �湮 �ð���  %d���� ���� �ð��Դϴ�.\n�ð��� ���� �湮�ϼ���!", answer);
				finish = -1; // ���� ū while �ݺ��� ���Ḧ ���� ������ ����
			} // else if �� ����
			System.out.println("\n=================================================");
		} // while�� ����
		System.out.println("\n�ý����� �����մϴ�.");
		sc.close();
	}// main method end

///////////////////////////////////////////////////////////////////////////
	
	// method ����

	// ������ �����ϱ� ���� method
	public static void DelV(int del, int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		int num = del - 1; // �ε��� = ���� ��ȣ-1
		if (num != 5) {// �����ϴ� ���� 5�� �ε����� �ƴ� ��쿡,
			for (int i = 0; i <= 2; i++) {
				for (int j = num; j <= 4; j++) {
					Vlist[i][j] = Vlist[i][j + 1];
					// Vlist���� ������ ������ �ε�������
					// �� ū �ε����� ���� �������� ������ �� ĭ�� ����.
				}
			}
			// VlistName, Classcnt�� ���ؼ��� �����ϰ� ����
			for (int i = num; i <= 4; i++) {
				VlistName[i] = VlistName[i + 1];
				Classcnt[i] = Classcnt[i + 1];
			}
		}
		// DelV�� ������ �� 6�� ����(5�� �ε���)�� ���� ������ �׻� �ʱ�ȭ�ȴ�.
		// 6�� ����(5�� �ε���)�� ������ ���� ����
		Vlist[1][5] = 0;
		Vlist[2][5] = -1;
		VlistName[5] = "";
		Classcnt[5] = new int[] { 0, 0, 0 };
		// ��� ������ ���� ���� �ʱ�ȭ
	}

	// ������ �߰��ϱ� ���� method
	public static void AddV(int newVWeight, String newVName, int Val, int Cnt[], int[][] Vlist, String[] VlistName,
			int[][] Classcnt) {
		int num = 0;

		// �߰��ϰ��� �ϴ� ������ index(��ġ) Ȯ��
		for (int i = 0; i <= 5; i++) {
			if (Vlist[1][i] > newVWeight) { // ����ġ�� ���� �迭�� ������������ ���ĵǾ������Ƿ�
				// ����ġ �񱳸� ���� ������ ��ġ�� ã���ش�.
				num += 1;
			}
		}

		// ������ Ȯ���ϱ� ����
		// ã�� �ε��� �̻��� �ε����� ���� �������� �� ĭ �� �ڷ� �о��ش�.
		for (int i = 5; i > num; --i) { // �Ųٷ� ������ �Ͽ�, ��� i���� ���� ���ܻ����� ������
			for (int j = 1; j <= 2; j++) {
				Vlist[j][i] = Vlist[j][i - 1];
			}
			VlistName[i] = VlistName[i - 1];
			Classcnt[i] = Classcnt[i - 1];
		}
		Vlist[1][num] = newVWeight;
		Vlist[2][num] = Val;
		VlistName[num] = newVName;
		Classcnt[num] = Cnt;
	}// �̹� 6 ������ �����ϴ� ��Ȳ�̸�, 6�� ������ �о�� ���ο� ������ �ڸ���´�.

	// ������ �̸� ���� ������ ���� method
	public static void Name(int modi, String modiName, String[] VlistName) {
		VlistName[modi - 1] = modiName;
	}

	// ������ ������ ���� ������ ���� method
	public static void Value(int modi, int modiVal, int[][] Vlist) {
		Vlist[2][modi - 1] = modiVal;
	}

	// ������ ������ ��ġ ���� ������ ���� method
	public static void Connect(int modi, int[] Cnt2, int[][] Classcnt) {
		Classcnt[modi - 1] = Cnt2;
	}

	// ��ü ������ ���� ������ ������ ����
	public static void PrintVLess(String[] VlistName) {
		for (int i = 0; i <= 5; i++) {
			if (VlistName[i].equals("") == false) {
				System.out.printf((i + 1) + "�� ����:[%s]\t", VlistName[i]);
			}
		}
	}

	// ��ü ������ ���� ���� ������ ����
	public static void PrintV(int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		for (int i = 0; i <= 5; i++) {
			if (Vlist[2][i] != -1) {
				System.out.printf((i + 1) + "�� ����:[������: %s], [����ġ: %d], [������: %d], [���� ��ġ: %s]\n", VlistName[i],
						Vlist[1][i], Vlist[2][i], Arrays.toString(Classcnt[i]));
			}
		}
	}

	// Ư���� �� ������ ���� ���� ������ ����
	public static void PrintVMore(int modi, int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		int i = modi - 1;
		System.out.printf(modi + "�� ����:[������: %s], [����ġ: %d], [������: %d], [���� ��ġ: %s]\n", VlistName[i], Vlist[1][i],
				Vlist[2][i], Arrays.toString(Classcnt[i]));
	}

	// ���� ��Ȳ�� ���ɼ� ��ġ_��� �� ��, ���� ��ü �л��� ���� �湮 ���ɼ��� ���� ���� ����
	public static int Calcul(int[] timeList, int[][] Vlist, int[][] Classcnt) {
		for (int j = 0; j <= 2; j++) {
			int total = 0; // ��Ȳ(���ú�) ���ɼ��� ��� ����
			for (int i = 0; i <= 5; i++) {
				// ���ɼ� ��ġ = �� �ð� ��� ��� ������ ����(��������ġ*������*����������)�� ����
				total += Vlist[1][i] * Vlist[2][i] * Classcnt[i][j];
			}
			// timeList�� ����ִ� ���� 0�̾ �Ұ����� �ð��밡 �ƴ϶��,
			// timeList�� �ش� ���� �ε����� total�� ����
			if (timeList[2 * j] != 0)
				timeList[2 * j] = total;
			if (timeList[2 * j + 1] != 0)
				timeList[2 * j + 1] = total;
		}
		// 7������ ���ɼ��� �׻� 5,6 ���ÿ� ���ٰ� �����صξ����Ƿ�,
		timeList[6] = timeList[5];

		// ���ɼ��� �ּ��� ��츦 ã�� ����
		// ���� �迭 ���� - timeList�� ���� ���� ����
		int[] orgtList = new int[7];
		System.arraycopy(timeList, 0, orgtList, 0, timeList.length);

		// ������ �ξ����Ƿ�, ���� ���� ���� �˱� ���� ����
		Arrays.sort(timeList);

		// �������� �����̹Ƿ�, timeList�� ������ �ε����� ���� �׻� �ִ�.
		// �ʱⰪ���� �̸� �д�.
		int min = timeList[6];

		for (int i = 0; i <= 6; i++) {
			if (timeList[i] != 0) {// 0�� �ƴ� ��쿡(������ �ð��뿡)
				if (timeList[i] < min) {
					min = timeList[i];
					break; // ���� ��, if������ �����ϴ� ù��° i���� �ּڰ��� ���̹Ƿ�, �ٷ� break
				}
			}
		}
		// �ּڰ��� ������ ���ø� ���ϱ� ���� ���� minindex����
		int minindex = 0;

		for (int i = 0; i <= 6; i++) {
			if (min == orgtList[i]) {// ������ �� orgtList�� min�� ���� ��
				minindex = i;
				break;
			}
		}
		// minindex = ����-1
		return (minindex + 1);
	}
}