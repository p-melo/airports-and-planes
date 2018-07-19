/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**<p>Thrown to indicate that a value is out of its acceptable ranges.</p>
 * <p>This is a subclass from the Exception class.</p>
 * @author Modusaleatorios
 */
public class OutOfRangeValuesException extends Exception
{
    public OutOfRangeValuesException(String message)
    {
        super(message);
    }
}
