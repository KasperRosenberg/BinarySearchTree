//Kasper Rosenberg karo5568

/**
 * 
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 * 
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alterntiv
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */
@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns här
							// tillfälligt för att vi inte ska tro att det är
							// fel i koden. Varningar ska normalt inte döljas på
							// detta sätt, de är (oftast) fel som ska fixas.
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