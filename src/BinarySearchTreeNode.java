//Kasper Rosenberg karo5568

/**
 * 
 * Detta �r den enda av de tre klasserna ni ska g�ra n�gra �ndringar i. (Om ni
 * inte vill l�gga till fler testfall.) Det �r ocks� den enda av klasserna ni
 * ska l�mna in. Gl�m inte att namn och anv�ndarnamn ska st� i en kommentar
 * h�gst upp, och att paketdeklarationen m�ste plockas bort vid inl�mningen f�r
 * att koden ska g� igenom de automatiska testerna.
 * 
 * De �ndringar som �r till�tna �r begr�nsade av f�ljande:
 * <ul>
 * <li>Ni f�r INTE byta namn p� klassen.
 * <li>Ni f�r INTE l�gga till n�gra fler instansvariabler.
 * <li>Ni f�r INTE l�gga till n�gra statiska variabler.
 * <li>Ni f�r INTE anv�nda n�gra loopar n�gonstans. Detta g�ller ocks� alterntiv
 * till loopar, s� som str�mmar.
 * <li>Ni F�R l�gga till fler metoder, dessa ska d� vara privata.
 * <li>Ni f�r INTE l�ta N�GON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni v�ljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer �r
 * till�tna.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */
@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns h�r
							// tillf�lligt f�r att vi inte ska tro att det �r
							// fel i koden. Varningar ska normalt inte d�ljas p�
							// detta s�tt, de �r (oftast) fel som ska fixas.
public class BinarySearchTreeNode<T extends Comparable<T>> {

	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	public BinarySearchTreeNode() {
		// TODO Auto-generated constructor stub
	}

	public boolean add(T data) {
		if (this.data.compareTo(data) > 0) {
			if (left == null) {
				left = new BinarySearchTreeNode(data);
				return true;
			} else {
				return left.add(data);
			}
		}
		if (this.data.compareTo(data) < 0) {
			if (right == null) {
				right = new BinarySearchTreeNode(data);
				return true;
			} else {
				return right.add(data);
			}
		} else {
			return false;
		}
	}

	private T findMin() {
		if (left != null) {
			return left.findMin();
		} else {
			return data;
		}
	}

	public BinarySearchTreeNode<T> remove(T data) {
		int result = this.data.compareTo(data);
		if (result > 0) {
			if (left == null) {
				return this;
			} else {
				left = left.remove(data);
			}
		} else if (result < 0) {
			if (right == null) {
				return this;
			} else {
				right = right.remove(data);
			}
		} else if (left != null && right != null) {
			this.data = right.findMin();
			right = right.remove(this.data);
		} else {
			if (left != null) {
				return left;
			} else {
				return right;
			}
		}
		return this;
	}

	public boolean contains(T data) {
		if (this.data.compareTo(data) == 0) {
			return true;
		} else if (this.data.compareTo(data) > 0 && left != null) {
			return left.contains(data);
		} else if (this.data.compareTo(data) < 0 && right != null) {
			return right.contains(data);
		}
		return false;

	}

	public int size() {
		if (left == null && right == null) {
			return 1;
		}
		if (left == null && right != null) {
			return 1 + right.size();
		}
		if (left != null && right == null) {
			return 1 + left.size();
		} else {
			return 1 + left.size() + right.size();
		}
	}

	public int depth() {
		if (this.data == null) {
			return 0;
		} else if (left == null && right == null) {
			return 1;
		} else if (left == null && right != null) {
			return right.depth();
		} else if (left != null && right == null) {
			return left.depth();
		} else {
			int leftDepth = left.depth() + 1;
			int rightDepth = right.depth() + 1;
			if (leftDepth > rightDepth) {
				return leftDepth;
			} else {
				return rightDepth;
			}
		}
	}

	public String toString() {
		if (this.data == null) {
			return "";
		}
		if (left == null && right == null) {
			return data.toString();
		}
		if (left != null && right != null) {
			return left.toString() + ", " + data.toString() + ", " + right.toString();
		}
		if (left != null && right == null) {
			return left.toString() + ", " + data.toString();
		} else {
			return data.toString() + ", " + right.toString();
		}
	}
}