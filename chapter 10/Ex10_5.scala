/**
* 10.5 The JavaBeans specification has the notion of a property change listener, a standardized way for beans to communicate changes in their properties.
* The PropertyChangeSupport class is provided as a convenience superclass for any bean that wishes to supprt property change listeners. 
* Unfortunately, a class that already has another superclass- such as JComponent- must reimplement the methods. 
* Reimplement PropertyChangeSupport as a trait, and mix it into the java.awt.Point class.
**/

object Ex10_5 extends App {

  trait PropertyChangeSupport {
    val pcs = new java.beans.PropertyChangeSupport()

    addPropertyChangeListener(PropertyChangeListener listener) {
      pcs.addPropertyChangeListener(listener)
    }
    addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      pcs.addPropertyChangeListener(propertyName, listener)
    }
    fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
      pcs.fireIndexedPropertyChange(propertyName,index,oldValue,newValue)
    }
    fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
      pcs.fireIndexedPropertyChange(propertyName,index,oldValue,newValue)
    }
    fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
      pcs.fireIndexedPropertyChange(propertyName,index,oldValue,newValue)
    }
    firePropertyChange(PropertyChangeEvent event) {
      pcs.firePropertyChange(event)
    }
    firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
      pcs.firePropertyChange(propertyName,oldValue,newValue)
    }
    firePropertyChange(String propertyName, int oldValue, int newValue) {
      pcs.firePropertyChange(propertyName, oldValue, newValue)
    }
    firePropertyChange(String propertyName,  oldValue : Any,  newValue : Any) {
      pcs.firePropertyChange(propertyName, oldValue, newValue)
    }
    getPropertyChangeListeners() = {
      pcs.getPropertyChangeListeners
    }
    getPropertyChangeListeners(String propertyName) = {
      pcs.getPropertyChangeListeners
    }
    hasListeners(String propertyName) = {
      pcs.hasListeners
    }
    removePropertyChangeListener(PropertyChangeListener listener) {
      pcs.removePropertyChangeListener(listener)
    }
    removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      pcs.removePropertyChangeListener(propertyName, listener)
    }
  }

  class PropertyChangeListener( cb : java.beans.PropertyChangeEvent => Unit) extends java.beans.PropertyChangeListener{
    def propertyChange( evt: java.beans.PropertyChangeEvent) {
      cb(evt)
    }
  }

  class MyBean extends java.beans.PropertyChangeSupport {
    private var _age = 0

    def age = _age

    def age_= (newAge : Int) { val oldValue = _age; _age = newAge; firePropertyChange("age", oldValue, age) }
  }

  class MyInheritBean extends javax.swing.JPanel with PropertyChangeSupport {
    private var _value = 0

    def value = _value

    def value_= (newValue : Int) { val oldValue = _value; _value = newValue; firePropertyChange("value", oldValue, value) }

  }



  val testBean = new MyBean

  var wasTriggered = false

  testBean.addPropertyChangeListener( new PropertyChangeListener( e => wasTriggered = true)) 

  assert(wasTriggered == false)
  testBean.age = 5
  assert(wasTriggered == true)
}

