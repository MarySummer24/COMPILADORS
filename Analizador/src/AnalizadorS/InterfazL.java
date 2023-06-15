package AnalizadorL;


import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.AttributeSet;
//import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.event.*;
import java.net.*;
import java.util.Vector;
import java.io.*;

class Datos
{
	private int op;

	public Datos(int op)
	{
		this.op = op;
	}
	public int Opcion()
	{
		return op;
	}
}

@SuppressWarnings("serial")
class ArchS extends JDialog
{
	private JLabel et,img;
	private JButton b1, b2;
	private Datos datl = null;
	int op = 0;

	public ArchS(JFrame owner, boolean modal)
	{
		super(owner, modal);
		setTitle("");
		setSize(400, 150);
		setResizable(false);
		setLocationRelativeTo(this);
		
		JPanel arr = new JPanel();
		URL ruta=getClass().getResource("/AnalizadorL/Recursos/GuardarA.png");
		img = new JLabel(new ImageIcon(ruta));
		arr.add(img);
		et = new JLabel("¿Desea guardar cambios en este archivo?");
		et.setFont(new Font("Arial",3, 15));
		et.setForeground(new Color(36, 8, 69));
		arr.add(et);
		add(arr,BorderLayout.CENTER);
		arr.setBackground(Color.WHITE);
		b1 = new JButton("Salir");
		b1.setFont(new Font("Arial",3, 15));
		b1.setForeground(new Color(36, 8, 69));
		b1.setBackground(Color.WHITE);
		b2 = new JButton("Guardar");
		b2.setFont(new Font("Arial",3, 15));
		b2.setForeground(Color.WHITE);
		b2.setBackground(new Color(36, 8, 69));
		b2.setBorder(BorderFactory.createLineBorder(new Color(36, 8, 69), 5));
		
		JPanel bot = new JPanel();

		bot.add(b2);
		bot.add(b1);
		add(bot, BorderLayout.SOUTH);
		bot.setBackground(Color.WHITE);
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				op = 1;
				datl = new Datos(op);
				dispose();
				setVisible(false);
			}
		});
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				op = 2;
				datl = new Datos(op);
				dispose();
				setVisible(false);
			}
		});
		datl = new Datos(op);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public Datos mostrarCuadDialog()
	{
		setVisible(true);
		return datl;
	}
}


 
@SuppressWarnings("serial")
public class InterfazL extends JFrame implements ActionListener
{
	private Sintactico objs = new Sintactico();
	private Semantico objm = new Semantico();
	private JMenuBar barraM;
	private JMenu opm1, opm2,opsub,opsub1,opsub2;
	private JMenuItem op2, op3, op4, op5, op6, op7,op8,op9,op10,op11,op12,op13,op14,op15,op16,op17,op18;
	private Archivo oba;
	private JButton b1, b2, b3, b4, b5,b6,b7;
	private JTextField ct;
	private JLabel im;
	private JTextPane artx;
	private JTextArea num,consola;
	private JFileChooser archivo;
	private ArchS Seg;
	private Datos da;
	private int op, tam=12,tl=0;
	private String letra="Arial", pal="for while do if main menu caso priv pub prot est clase void int char doble largo float cad corto ban final new v f null this try catch else super pi e pow print";
	;
	int pos=0,antpos=0;
	private JTabbedPane pestañas;
	private boolean col;
	private StyleContext sc;
	private DefaultStyledDocument doc;
	private Lexer lexer;
	private Reader lector;
	JTable tabla,tabla1;
	DefaultTableModel modelo,modelo1;
	Vector<String> titulos,titulos1;
	Vector<Vector> datos,datos1;
	String pil="",esint="";
	boolean ban=false;
	public InterfazL()
	{
		setTitle("CatCode");
		setSize(1000, 600);
		setLocationRelativeTo(this);
		Image icon = new ImageIcon(getClass().getResource("/AnalizadorL/Recursos/RutaA.png")).getImage();
        setIconImage(icon);
        
		JPanel arr = new JPanel();
		URL ruta = getClass().getResource("/AnalizadorL/Recursos/RutaA.png");
		im = new JLabel(new ImageIcon(ruta));
		ct =new JTextField(75);
		ct.setEnabled(false);
		ct.setDisabledTextColor(new Color(175, 223, 248));
		ct.setBackground(/*new Color(105, 105, 255)*/new Color(36, 8, 69));
		ct.setFont(new Font(letra,Font.BOLD,13));
		ct.setBorder(null);
		arr.setBackground(new Color(36, 8, 69));
		arr.add(im);
		arr.add(ct);
		add(arr, BorderLayout.SOUTH);
		
		barraM = new JMenuBar();
		setJMenuBar(barraM);
		opm1 = new JMenu("Archivo");
		opm2 = new JMenu("Ajustes");
		barraM.add(opm1);
		barraM.add(opm2);
				
		ruta=getClass().getResource("/AnalizadorL/Recursos/ArchivoA.png");
		op2 = new JMenuItem("Nuevo              ",new ImageIcon(ruta));
		op2.setMnemonic('R');
		op2.setToolTipText("Crea un archivo nuevo");
		op2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		opm1.add(op2);
		
		ruta=getClass().getResource("/AnalizadorL/Recursos/GuardarA.png");
		op3 = new JMenuItem("Guardar",new ImageIcon(ruta));
		op3.setMnemonic('R');
		op3.setToolTipText("Guarda el archivo");
		op3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		
		
		ruta=getClass().getResource("/AnalizadorL/Recursos/AbrirA.png");
		op4 = new JMenuItem("Abrir",new ImageIcon(ruta));
		op4.setMnemonic('R');
		op4.setToolTipText("Abre un archivo");
		op4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		opm1.add(op4);
		opm1.add(op3);
		
		ruta=getClass().getResource("/AnalizadorL/Recursos/CerrarA.png");
		op6 = new JMenuItem("Cerrar",new ImageIcon(ruta));
		op6.setMnemonic('R');
		op6.setToolTipText("Cierra el archivo");
		op6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		opm1.add(op6);
		
		opm1.addSeparator();
		op5 = new JMenuItem("Salir");
		op5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		opm1.add(op5);
		barraM.setBackground(Color.WHITE);

		
		opsub1 = new JMenu("Zoom              ");
		opm2.add(opsub1);
		ruta=getClass().getResource("/AnalizadorL/Recursos/acercarseG.png");
		op7 = new JMenuItem("Incrementar",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/disminuirG.png");
		op18 = new JMenuItem("Disminuir",new ImageIcon(ruta));
		opsub1.add(op7);
		opsub1.add(op18);
		
		opsub2 = new JMenu("Fuente              ");
		opm2.add(opsub2);
		ruta=getClass().getResource("/AnalizadorL/Recursos/ArialG.png");
		op8 = new JMenuItem("Arial",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/SansG.png");
		op9 = new JMenuItem("Sans Serif",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/HelvG.png");
		op11 = new JMenuItem("Helvetica",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/CalibriG.png");
		op12 = new JMenuItem("Calibri",new ImageIcon(ruta));
		opsub2.add(op8);
		opsub2.add(op9); 
		opsub2.add(op11);
		opsub2.add(op12); 
		
		opsub = new JMenu("Tema              ");
		opm2.add(opsub);
		ruta=getClass().getResource("/AnalizadorL/Recursos/BlancoG.png");
		op13 = new JMenuItem("Claro",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/NegroG.png");
		op14 = new JMenuItem("Obscuro",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/AzulCG.png");
		op15 = new JMenuItem("Azul claro",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/AzulOG.png");
		op16 = new JMenuItem("Azul obscuro",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/GrisG.png");
		op17 = new JMenuItem("Gris",new ImageIcon(ruta));
		ruta=getClass().getResource("/AnalizadorL/Recursos/AzulG.png");
		op10 = new JMenuItem("Azul",new ImageIcon(ruta));
		opsub.add(op13);
		opsub.add(op17);
		opsub.add(op14);
		opsub.add(op15);
		opsub.add(op10);
		opsub.add(op16);
		
		
		JPanel bot = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ruta = getClass().getResource("/AnalizadorL/Recursos/TrianguloA.png");
		b1 = new JButton(new ImageIcon(ruta));
		b1.setToolTipText("Run");
		b1.setBackground(new Color(235, 235, 255));
		b1.setBorder(null);
		ruta = getClass().getResource("/AnalizadorL/Recursos/ArchivoA.png");
		b2 = new JButton(new ImageIcon(ruta));
		b2.setToolTipText("Crea un archivo nuevo");
		b2.setBackground(new Color(235, 235, 255));
		b2.setBorder(null);
		ruta=getClass().getResource("/AnalizadorL/Recursos/acercarseG.png");
		b3 = new JButton(new ImageIcon(ruta));
		b3.setToolTipText("Zoom +");
		b3.setBackground(new Color(235, 235, 255));
		b3.setBorder(null);
		ruta=getClass().getResource("/AnalizadorL/Recursos/disminuirG.png");
		b4 = new JButton(new ImageIcon(ruta));
		b4.setToolTipText("Zoom -");
		b4.setBackground(new Color(235, 235, 255));
		b4.setBorder(null);
		ruta=getClass().getResource("/AnalizadorL/Recursos/GuardarA.png");
		b5 = new JButton(new ImageIcon(ruta));
		b5.setToolTipText("Guarda el contenido del archivo de texto");
		b5.setBackground(new Color(235, 235, 255));
		b5.setBorder(null);
		ruta=getClass().getResource("/AnalizadorL/Recursos/AbrirA.png");
		b6 = new JButton(new ImageIcon(ruta));
		b6.setToolTipText("Abre un archivo");
		b6.setBackground(new Color(235, 235, 255));
		b6.setBorder(null);
		ruta=getClass().getResource("/AnalizadorL/Recursos/CerrarA.png");
		b7 = new JButton(new ImageIcon(ruta));
		b7.setToolTipText("Cerrar archivo");
		b7.setBackground(new Color(235, 235, 255));
		b7.setBorder(null);
		
		bot.add(b2);
		bot.add(b6);
		bot.add(b5);
		bot.add(b7);
		bot.add(b1);
		bot.add(b3);
		bot.add(b4);
		
		JComboBox<ImageIcon> comboBox = new JComboBox<ImageIcon>();
		ruta=getClass().getResource("/AnalizadorL/Recursos/ArialM.png");
        comboBox.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/SansM.png");
        comboBox.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/HelvM.png");
        comboBox.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/CalibriM.png");
        comboBox.addItem(new ImageIcon(ruta));
        bot.add(comboBox);
        
        JComboBox<ImageIcon> comboBox2 = new JComboBox<ImageIcon>();
		ruta=getClass().getResource("/AnalizadorL/Recursos/TemaC.png");
        comboBox2.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/TemaG.png");
        comboBox2.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/TemaO.png");
        comboBox2.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/TemaAc.png");
        comboBox2.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/TemaA.png");
        comboBox2.addItem(new ImageIcon(ruta));
        ruta=getClass().getResource("/AnalizadorL/Recursos/TemaAo.png");
        comboBox2.addItem(new ImageIcon(ruta));
        bot.add(comboBox2);
        
		bot.setBackground(new Color(235, 235, 255));
		add(bot, BorderLayout.NORTH);
		
		pestañas=new JTabbedPane();    	
		
		JPanel p=new JPanel(new BorderLayout());
		JScrollPane scroll = new JScrollPane(p);
		
		num = new JTextArea();
		num.setBackground(Color.WHITE);
		num.setFont(new Font(letra,Font.BOLD,tam));
		num.setBackground(new Color(235, 235, 255));
		p.add(num,BorderLayout.WEST);
		artx = new JTextPane();
        artx.setBackground(new Color(220, 220, 255));
        artx.setFont(new Font(letra,Font.PLAIN,tam));
        p.add(artx);
		pestañas.addTab("Archivo.mal", scroll);
		ruta=getClass().getResource("/AnalizadorL/Recursos/iconArc.png");
		pestañas.setIconAt(0, new ImageIcon(ruta));
        pestañas.setBackgroundAt(0,Color.WHITE);
        
        this.sc=new StyleContext();
        this.doc=new DefaultStyledDocument(sc);
        
		getContentPane().add(pestañas);
        JPanel p2=new JPanel(new BorderLayout());
		JScrollPane scroll2 = new JScrollPane(p2);
        consola = new JTextArea();
		consola.setBackground(Color.WHITE);
		consola.setFont(new Font(letra,Font.PLAIN,tam));
        p2.add(consola);
        pestañas.addTab("Consola", scroll2);
        ruta=getClass().getResource("/AnalizadorL/Recursos/Consola.png");
		pestañas.setIconAt(1, new ImageIcon(ruta));
        pestañas.setBackgroundAt(1,Color.WHITE);
        
        
        getContentPane().add(pestañas);
		titulos = new Vector<String>();
		titulos.add("Token");
		titulos.add("Patron");
		titulos.add("Componente");
		datos = new Vector<Vector>();
		modelo = new DefaultTableModel(datos, titulos);
		tabla = new JTable(modelo);
		tabla.setFont(new Font(letra,Font.PLAIN,tam));
		tabla.setRowHeight(tam);
		tabla.getTableHeader().setFont(new Font(letra,Font.PLAIN,tam));
		JScrollPane scroll3 = new JScrollPane(tabla);
        pestañas.addTab("Componentes", scroll3);
        ruta=getClass().getResource("/AnalizadorL/Recursos/Tabla.png");
		pestañas.setIconAt(2, new ImageIcon(ruta));
        pestañas.setBackgroundAt(2,Color.WHITE);
        
        getContentPane().add(pestañas);
		titulos1 = new Vector<String>();
		titulos1.add("Pila");
		titulos1.add("Entrada");
		titulos1.add("Acción");
		datos1 = new Vector<Vector>();
		modelo1 = new DefaultTableModel(datos1, titulos1);
		tabla1 = new JTable(modelo1);
		tabla1.setFont(new Font(letra,Font.PLAIN,tam));
		tabla1.setRowHeight(tam);
		tabla1.getTableHeader().setFont(new Font(letra,Font.PLAIN,tam));
		JScrollPane scroll4 = new JScrollPane(tabla1);
        pestañas.addTab("Pila", scroll4);
		pestañas.setIconAt(3, new ImageIcon(ruta));
        pestañas.setBackgroundAt(3,Color.WHITE);
		
		archivo = new JFileChooser();
		oba = new Archivo();
		
		col=true;
		num.setEditable(false);
		consola.setEditable(false);
		artx.setEnabled(false);
		b1.setEnabled(false);
		b5.setEnabled(false);
		op6.setEnabled(false);
		op3.setEnabled(false);
		b7.setEnabled(false);
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("MAL Documentos", "mal");
		archivo.setFileFilter(filtro);

		op2.addActionListener(this);
		op3.addActionListener(this);
		op4.addActionListener(this);
		op6.addActionListener(this);
		op7.addActionListener(this);
		op8.addActionListener(this);
		op9.addActionListener(this);
		op10.addActionListener(this);
		op11.addActionListener(this);
		op12.addActionListener(this);
		op13.addActionListener(this);
		op14.addActionListener(this);
		op15.addActionListener(this);
		op16.addActionListener(this);
		op17.addActionListener(this);
		op18.addActionListener(this);
		op5.addActionListener(this);	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b5.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		artx.addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_SPACE|e.getKeyCode()==KeyEvent.VK_ENTER|e.getKeyCode()==KeyEvent.VK_TAB)
				{
					//Palabra();
//					lector = new BufferedReader(new StringReader(artx.getText()));
//					lexer = new Lexer(lector);
//					//lexer.
//					pintar(lexer.posi,lexer.posf);
//					Estilo(lexer.lexeme);
//					artx.setDocument(artx.getDocument());
	            }
				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
					pos--;
					antpos--;
					Contar();}
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					Contar();
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					pos--;
					antpos--;
					Contar();
				}
			}
			@Override
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
		});	
		comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				switch (comboBox.getSelectedIndex())
				{
				case 0:
					letra="Arial";
					Fuente(tam, letra);
					break;
				case 1:
					letra="Sans Serif";
					Fuente(tam, letra);
					break;
				case 2:
					letra="Helvética";
					Fuente(tam, letra);
					break;
				case 3:
					letra="Calibri";
					Fuente(tam, letra);
					break;
				}
			}
		});
		comboBox2.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				switch (comboBox2.getSelectedIndex())
				{
				
				case 0:
					col=false;
					//num.setBackground(Color.WHITE);
					artx.setBackground(Color.WHITE);
					num.setForeground(Color.BLACK);
					artx.setForeground(Color.BLACK);
					
					consola.setBackground(Color.WHITE);
					consola.setForeground(Color.BLACK);
					
					tabla.setBackground(Color.WHITE);
					tabla.setForeground(Color.BLACK);
					
					tabla.getTableHeader().setForeground(Color.WHITE);
					tabla.getTableHeader().setBackground(Color.BLACK);
					break;
				case 2:
					col=false;
					num.setBackground(Color.DARK_GRAY);
					artx.setBackground(Color.BLACK);
					num.setForeground(Color.WHITE);
					artx.setForeground(Color.WHITE);
					
					consola.setBackground(Color.BLACK);
					consola.setForeground(Color.WHITE);
					
					tabla.setBackground(Color.BLACK);
					tabla.setForeground(Color.WHITE);
					tabla.getTableHeader().setForeground(Color.WHITE);
					tabla.getTableHeader().setBackground(Color.BLACK);
					break;
				case 3:
					col=false;
					num.setBackground(new Color(22,8,90));
					artx.setBackground(new Color(217,239,255));
					num.setForeground(Color.WHITE);
					artx.setForeground(Color.BLACK);
					
					consola.setBackground(new Color(217,239,255));
					consola.setForeground(Color.BLACK);
					
					tabla.setBackground(new Color(217,239,255));
					tabla.setForeground(Color.BLACK);
					tabla.getTableHeader().setForeground(new Color(217,239,255));
					tabla.getTableHeader().setBackground(Color.BLACK);
					break;
				case 5:
					col=false;
					num.setBackground(new Color(217,239,255));
					artx.setBackground(new Color(22,8,90));
					num.setForeground(Color.BLACK);
					artx.setForeground(Color.WHITE);
					
					consola.setBackground(new Color(22,8,90));
					consola.setForeground(Color.WHITE);
					
					tabla.setBackground(new Color(22,8,90));
					tabla.setForeground(Color.WHITE);
					tabla.getTableHeader().setForeground(new Color(22,8,90));
					tabla.getTableHeader().setBackground(Color.WHITE);
					break;
				case 1:
					col=false;
					num.setBackground(Color.GRAY);
					artx.setBackground(Color.LIGHT_GRAY);
					num.setForeground(Color.WHITE);
					artx.setForeground(Color.BLACK);
					
					consola.setBackground(Color.LIGHT_GRAY);
					consola.setForeground(Color.BLACK);
					
					tabla.setBackground(Color.LIGHT_GRAY);
					tabla.setForeground(Color.BLACK);
					tabla.getTableHeader().setForeground(Color.LIGHT_GRAY);
					tabla.getTableHeader().setBackground(Color.BLACK);
					break;
				case 4:
					col=false;
					num.setBackground(new Color(217,239,255));
					artx.setBackground(new Color(185,216,237));
					num.setForeground(new Color(6,58,95));
					artx.setForeground(new Color(32,70,96));
					
					consola.setBackground(new Color(185,216,237));
					consola.setForeground(new Color(32,70,96));
					
					tabla.setBackground(new Color(185,216,237));
					tabla.setForeground(new Color(32,70,96));
					tabla.getTableHeader().setForeground(new Color(185,216,237));
					tabla.getTableHeader().setBackground(new Color(32,70,96));
					break;
				}
			}
		});
		
		setVisible(true);
		addWindowListener(new WindowListener(){

            public void windowActivated(WindowEvent e){}

            public void windowClosed(WindowEvent e){}

            public void windowDeactivated(WindowEvent e){}

            public void windowDeiconified(WindowEvent e){}

            public void windowIconified(WindowEvent e){}

            public void windowOpened(WindowEvent e){}

            public void windowClosing(WindowEvent e){

            if(artx.isEnabled())
            	Cerrar();

            e.getWindow().dispose();
            }
            
		});
	}
	
	
	public static void main(String[] args)
	{
		new InterfazL();
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			this.Guardar();
			pestañas.setSelectedIndex(1);
			try
			{
				this.Run();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == op2||e.getSource() == b2)
			this.CrearArc();
		if(e.getSource() == op3||e.getSource() == b5)
			this.Guardar();
		if(e.getSource() == op4||e.getSource() == b6)
			this.AbrirArc();
		if(e.getSource() == op5)
		{
			if(artx.isEnabled())
				this.Cerrar();
			System.exit(0);
		}
		if(e.getSource() == op6 ||e.getSource() == b7) 
		{
			this.Cerrar();
			num.setText("1 \n");
		}
		if(e.getSource() == op7||e.getSource() == b3)
		{
			tam+=2;
			this.Fuente(tam, letra);
		}
		if(e.getSource() == op18||e.getSource() == b4)
		{
			tam-=2;
			this.Fuente(tam, letra);
		}
			
		if(e.getSource() == op8)
		{
			letra="Arial";
			this.Fuente(tam, letra);
		}
		if(e.getSource() == op9)
		{
			letra="Sans Serif";
			this.Fuente(tam, letra);
		}
		if(e.getSource() == op11)
		{
			letra="Helvética";
			this.Fuente(tam, letra);
		}
		if(e.getSource() == op12)
		{
			letra="Calibri";
			this.Fuente(tam, letra);
		}
		if(e.getSource() == op13)
		{
			col=false;
			//num.setBackground(Color.WHITE);
			artx.setBackground(Color.WHITE);
			num.setForeground(Color.BLACK);
			artx.setForeground(Color.BLACK);
			
			consola.setBackground(Color.WHITE);
			consola.setForeground(Color.BLACK);
			
			tabla.setBackground(Color.WHITE);
			tabla.setForeground(Color.BLACK);
			
			tabla.getTableHeader().setForeground(Color.WHITE);
			tabla.getTableHeader().setBackground(Color.BLACK);
		}
		if(e.getSource() == op14)
		{
			col=false;
			num.setBackground(Color.DARK_GRAY);
			artx.setBackground(Color.BLACK);
			num.setForeground(Color.WHITE);
			artx.setForeground(Color.WHITE);
			
			consola.setBackground(Color.BLACK);
			consola.setForeground(Color.WHITE);
			
			tabla.setBackground(Color.BLACK);
			tabla.setForeground(Color.WHITE);
			tabla.getTableHeader().setForeground(Color.WHITE);
			tabla.getTableHeader().setBackground(Color.BLACK);
		}
		if(e.getSource() == op15)
		{
			col=false;
			num.setBackground(new Color(22,8,90));
			artx.setBackground(new Color(217,239,255));
			num.setForeground(Color.WHITE);
			artx.setForeground(Color.BLACK);
			
			consola.setBackground(new Color(217,239,255));
			consola.setForeground(Color.BLACK);
			
			tabla.setBackground(new Color(217,239,255));
			tabla.setForeground(Color.BLACK);
			tabla.getTableHeader().setForeground(new Color(217,239,255));
			tabla.getTableHeader().setBackground(Color.BLACK);
		}
		if(e.getSource() == op16)
		{
			col=false;
			num.setBackground(new Color(217,239,255));
			artx.setBackground(new Color(22,8,90));
			num.setForeground(Color.BLACK);
			artx.setForeground(Color.WHITE);
			
			consola.setBackground(new Color(22,8,90));
			consola.setForeground(Color.WHITE);
			
			tabla.setBackground(new Color(22,8,90));
			tabla.setForeground(Color.WHITE);
			tabla.getTableHeader().setForeground(new Color(22,8,90));
			tabla.getTableHeader().setBackground(Color.WHITE);
		}
		if(e.getSource() == op17)
		{
			col=false;
			num.setBackground(Color.GRAY);
			artx.setBackground(Color.LIGHT_GRAY);
			num.setForeground(Color.WHITE);
			artx.setForeground(Color.BLACK);
			
			consola.setBackground(Color.LIGHT_GRAY);
			consola.setForeground(Color.BLACK);
			
			tabla.setBackground(Color.LIGHT_GRAY);
			tabla.setForeground(Color.BLACK);
			tabla.getTableHeader().setForeground(Color.LIGHT_GRAY);
			tabla.getTableHeader().setBackground(Color.BLACK);
		}
		if(e.getSource() == op10)
		{
			col=false;
			num.setBackground(new Color(217,239,255));
			artx.setBackground(new Color(185,216,237));
			num.setForeground(new Color(6,58,95));
			artx.setForeground(new Color(32,70,96));
			
			consola.setBackground(new Color(185,216,237));
			consola.setForeground(new Color(32,70,96));
			
			tabla.setBackground(new Color(185,216,237));
			tabla.setForeground(new Color(32,70,96));
			tabla.getTableHeader().setForeground(new Color(185,216,237));
			tabla.getTableHeader().setBackground(new Color(32,70,96));
		}
	}
	public void CrearArc() {
		archivo.setDialogTitle("Crear en: ");
		int returnVal =archivo.showSaveDialog(null);
		File ruta=null;
	    archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	          ruta = archivo.getSelectedFile();
	    
	    String exte = null;
	    try
	    {
	    	exte = ruta.getName();
			if(exte.lastIndexOf(".") != -1 && exte.lastIndexOf(".") != 0)
			   	exte.substring(exte.lastIndexOf(".") + 1);
			else
			    exte = null;
	    }
	    catch(NullPointerException s)
	    {}
	    
	    if(exte != null)
	    {
	    	int res = JOptionPane.showConfirmDialog(null, "El archivo ya existe \n¿Desea reemplazarlo?", "", JOptionPane.YES_NO_OPTION);
			if(res == 0)
			{
	    		String noc = null;
			    try
			    {
			    	noc = ruta.toString();
			    	oba.Borrar(noc);
			    }
			    catch(NullPointerException g)
			    {}
			    boolean ban = oba.Crear(noc);
			    if(ban)
			    {
			    	ct.setText(noc);
					oba.Abrir(noc);
					oba.Mostrar();
					op6.setEnabled(true);
					op3.setEnabled(true);
					b7.setEnabled(true);
					b5.setEnabled(true);
					b1.setEnabled(true);
					artx.setEnabled(true);
					
					if(col)
					{
						//num.setBackground(Color.WHITE);
						artx.setBackground(Color.WHITE);
						num.setForeground(Color.BLACK);
						artx.setForeground(Color.BLACK);
					}

					JOptionPane.showMessageDialog(null, "Archivo reemplazado");		
			    }
			    else;
			  //  	JOptionPane.showMessageDialog(null, "Error: Ruta requerida", "Archivo no creado",JOptionPane.ERROR_MESSAGE);
			    
	    	}
	    }
	    else
	    {
	    	String noc = null;
		    try
		    {
		    	noc = ruta.toString();
		    	noc += ".mal";
		    }
		    catch(NullPointerException g)
		    {}
		    boolean ban = oba.Crear(noc);
		    if(ban)
		    {
		    	ct.setText(noc);
				oba.Abrir(noc);
				oba.Mostrar();
				artx.setEnabled(true);
				op6.setEnabled(true);
				b7.setEnabled(true);
				op3.setEnabled(true);
				b5.setEnabled(true);
				b1.setEnabled(true);
				
				if(col)
				{
					//num.setBackground(Color.WHITE);
					artx.setBackground(Color.WHITE);
					num.setForeground(Color.BLACK);
					artx.setForeground(Color.BLACK);
				}
				
		    }
		    else;
		    	//JOptionPane.showMessageDialog(null, "Error: Ruta requerida", "Archivo no creado",JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void AbrirArc() {
			artx.setText("");
			archivo.setDialogTitle("Escoja un archivo");
			//archivo.showOpenDialog(null);
			File ruta = null;
		    int returnVal = archivo.showOpenDialog(null);
		    archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    if(returnVal == JFileChooser.APPROVE_OPTION) 
		    	 ruta = archivo.getSelectedFile();
		   
		    String noc = null;
		    try
		    {
		    	noc = ruta.toString();
		    }
		    catch(NullPointerException g)
		    {}
		    
		    boolean ban = oba.Abrir(noc);
			if(ban)
			{
				ct.setText(noc);
				op6.setEnabled(false);
				b7.setEnabled(false);
				op3.setEnabled(false);
				b5.setEnabled(false);
				b1.setEnabled(true);
				this.Mostrar();
				artx.setEnabled(true);
				
				if(col)
				{
					//num.setBackground(Color.WHITE);
					artx.setBackground(Color.WHITE);
					num.setForeground(Color.BLACK);
					artx.setForeground(Color.BLACK);
				}
//				this.Palabra();				
			}
			else
			{
				artx.setText("");
				ct.setText("");
//				JOptionPane.showMessageDialog(null, "Error: No se puede crear el archivo \nUsted selecciono abrir", "Error: Accion invalida",JOptionPane.ERROR_MESSAGE);
			}
			this.Contar();
	}

	public void Cerrar() {
		if(op == 1)
		{
			oba.Cerrar();
			op2.setEnabled(true);
			b2.setEnabled(true);
			op4.setEnabled(true);
			b6.setEnabled(true);
			op6.setEnabled(false);
			b7.setEnabled(false);
			op3.setEnabled(false);
			b5.setEnabled(false);
			b1.setEnabled(false);
			ct.setText("");
			artx.setText("");
			artx.setEnabled(false);

	        artx.setBackground(new Color(220, 220, 255));
		}
		else
		{
			Seg = new ArchS(InterfazL.this, true);
			da = Seg.mostrarCuadDialog();
			int opc = da.Opcion();
			if(opc == 1) 
			{
				oba.Cerrar();
				op2.setEnabled(true);
				b2.setEnabled(true);
				op4.setEnabled(true);
				b6.setEnabled(true);
				op6.setEnabled(false);
				b7.setEnabled(false);
				op3.setEnabled(false);
				b1.setEnabled(false);
				b5.setEnabled(false);
				ct.setText("");
				artx.setText("");
				artx.setEnabled(false);

		        artx.setBackground(new Color(220, 220, 255));
			}
			if(opc == 2)
			{
				String rug = ct.getText();
				String texg = artx.getText();
				oba.Grabar(rug, texg);
				oba.Cerrar();
				op2.setEnabled(true);
				b2.setEnabled(true);
				op4.setEnabled(true);
				b6.setEnabled(true);
				op6.setEnabled(false);
				b7.setEnabled(false);
				op3.setEnabled(false);
				b1.setEnabled(false);
				b5.setEnabled(false);
				ct.setText("");
				artx.setText("");
				artx.setEnabled(false);

		        artx.setBackground(new Color(220, 220, 255));
			}
		}
	}

	public void Guardar()
	{
		String rug = ct.getText();
		String texg = artx.getText();
		oba.Grabar(rug, texg);
		op = 1;	
	}

	public void Mostrar()
	{
		String cont = oba.Mostrar();
		op = -1;
		op6.setEnabled(true); //cerrar
		b7.setEnabled(true);
		op3.setEnabled(true);
		b5.setEnabled(true); //guardar
		b1.setEnabled(true);
		artx.setEnabled(true);
		artx.setText(cont);
		
	}

	public void Fuente(int tam, String letra) {
		num.setFont(new Font(letra,Font.BOLD,tam));
		artx.setFont(new Font(letra,Font.PLAIN,tam));
		consola.setFont(new Font(letra,Font.PLAIN,tam));
		tabla.setFont(new Font(letra,Font.PLAIN,tam));
		tabla.setRowHeight(tam);
		tabla.getTableHeader().setFont(new Font(letra,Font.PLAIN,tam));
		tabla1.setFont(new Font(letra,Font.PLAIN,tam));
		tabla1.setRowHeight(tam);
		tabla1.getTableHeader().setFont(new Font(letra,Font.PLAIN,tam));
	}
	
	public void Run() throws IOException{
		ban=false;
		objs = new Sintactico();
		objm = new Semantico();
		pil="";
		consola.setText("");
		File archivo = new File("archivo.txt");
		try {
			DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
			int filas=tabla.getRowCount();
			for (int i = 0;i<=filas; i++) {
			modelo.removeRow(0);
			}
			} catch (Exception e) {
			}
		try {
			DefaultTableModel modelo1=(DefaultTableModel) tabla1.getModel();
			int filas=tabla1.getRowCount();
			for (int i = 0;i<=filas; i++) {
			modelo1.removeRow(0);
			}
			} catch (Exception e) {
			}
		PrintWriter escribir;
		try
		{
			escribir = new PrintWriter(archivo);
			escribir.print(artx.getText());
			escribir.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			lector = new BufferedReader(new FileReader("archivo.txt"));
			lexer = new Lexer(lector);
			String resultado ="";
			int i=1;
			String taux;
			
			while (true)
			{
				if(ban)
					return;
				Tokens tokens = lexer.yylex();
				if(tokens == null)
				{
					consola.setText(resultado);
					consola.setText(esint);
					ASintactico("$",i);
					return;
				}
				Vector<String> v = new Vector<String>();
			
				switch(tokens) {
				case ERROR:
					resultado+="Error lexico en la linea "+i+": el simbolo no esta definido en el lenguaje";
					consola.setText(resultado);
					return;
					
				case id: 
					v.add(lexer.lexeme);
					v.add(tokens+"");
					v.add(tokens+"");
					modelo.addRow(v);
					//Agrege
					ASintactico(tokens+"",i);
					ASemantico(lexer.lexeme,tokens+"",i);
					break;
					
				case chara: case inta: case floata: 
					v.add(lexer.lexeme);
					taux= tokens.toString();
					taux=taux.substring(0, taux.length()-1);
					v.add(taux+"");
					v.add(taux+"");
					modelo.addRow(v);
					//Agrege
					ASintactico(taux+"",i);
					ASemantico(lexer.lexeme,"td",i);
					break;

				case reservada: 
					v.add(lexer.lexeme);
					v.add(tokens+"");
					v.add(lexer.lexeme);
					modelo.addRow(v);
					//Agrege
					ASintactico(lexer.lexeme,i);
					ASemantico(lexer.lexeme,lexer.lexeme,i);
					break;
				case num: 
					v.add(lexer.lexeme);
					v.add(tokens+"");
					v.add("num");
					modelo.addRow(v);
					//Agrege
					ASintactico("num",i);
					ASemantico(lexer.lexeme,"num",i);
					break;
				case salto:
					i++;
					break;
				case agrupacion: case aritmetico: case asignacion: case finSentencia: case coma: 
					v.add(lexer.lexeme);
					v.add(tokens+"");
					v.add(lexer.lexeme);
					//System.out.println(v);
					modelo.addRow(v);
					//Agrege
					ASintactico(lexer.lexeme,i);
					ASemantico(lexer.lexeme,lexer.lexeme,i);
				    break;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ASintactico(String comp, int lin) {
		Vector<String> a,pi = new Vector<String>();
		objs.ASintactico(comp);
		pil=pil+comp;
		Vector<String> p = new Vector<String>();
		//Tengo que imprimir cada que cambia la acción no el componente
		a = objs.Accion();
		pi= objs.Pilas();
		//System.out.println(a.size()+"");
		//System.out.println(pi.size()+"");
		//System.out.println(a);
		for(int i=0;i<a.size();i++)
		{
			//System.out.println(a.elementAt(i));
			//System.out.println(pi.elementAt(i));
			p.add(pil);
			p.add(pi.elementAt(i));
			p.add(a.elementAt(i));
			//System.out.println(a.elementAt(i));
			modelo1.addRow(p);
			if(a.elementAt(i).equals("Aceptada"))
				consola.setText("Succesfully!!!");
			if(a.elementAt(i).equals("Error"))
			{
				//System.out.println("Entro");
				//System.out.println(objs.error.substring(0,objs.error.length()-1)+esint+" en la linea "+lin);
				//esint=objs.error.substring(0,objs.error.length()-1)+esint+" en la linea "+lin;
				//Detener
				consola.setText(objs.error.substring(0,objs.error.length()-1)+esint+" en la linea "+lin);
				//Detener
				ban=true;
				
			}
			
			//System.out.println(p);
			p = new Vector<String>();
		}
		objs.ReinicarAcciones();
		a.clear();
	}

	
	public void Contar() {
		tl=1;
		num.setText(tl+"\n");
		for (int i = 0; i <artx.getText().length(); i++)
		{
			if(artx.getText().charAt(i)=='\n') {
				tl++;
			num.setText(num.getText()+tl+" \n");
			}
		}		
	}
	public void Palabra() {
			pos=(artx.getText().length()-1);
			System.out.println(antpos+"  "+pos);
			
			String pala=artx.getText().substring(antpos,pos);
			
			System.out.println(pala);
			if(pal.contains(pala))
			{
				this.pintar(antpos, pos);
				artx.setDocument(doc);
//				try
//				{
//					doc.insertString(0, artx.getText(), null);
//				}
//				catch (Exception e)
//				{
//					// TODO: handle exception
//					System.out.println("ERROR EN EL ESTILO");
//				}
			}
			Style color=sc.addStyle("ConstantWidth", null);
			StyleConstants.setForeground(color, Color.BLACK);
			doc.setCharacterAttributes(antpos, pos, color, false);
			antpos=pos+1;
			
	}
//	public void extraer() {
//		for (int i = 0; i <artx.getText().length(); i++)
//		{
//			if(artx.getText().charAt(i)=='\n')
//				pos--;
//			String pala=artx.getText().substring(antpos,pos);
//			antpos=pos+1;
//			System.out.println(pala);
//		}	
//	}
	
	public void Estilo(String txtCol) {
		
	}
	public void pintar(int pos1,int pos2) {
		Style color=sc.addStyle("ConstantWidth", null);
		StyleConstants.setForeground(color, Color.RED);
		doc.setCharacterAttributes(pos1, pos2, color, false);
	}
	
	public void ASemantico(String token,String comp,int i) {
		if(objm.error.equals(""))
			objm.ASemantico(token, comp);
		else
		{
			consola.setText("Error en la linea "+i+": "+objm.error);
			ban=true;
		}
			
	}
}