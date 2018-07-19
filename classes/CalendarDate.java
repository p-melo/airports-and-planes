package classes;

import Exceptions.OutOfRangeValuesException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**<p>The CalendarDate class represents a date in time. All dates in this program, such as hours, days or months, are implemented as instances of this.</p>
 * <p>The CalendarDate gets methods from Calendar class and GregorianCalendar class.</p>
 * <p>The CalendarDate class includes methods to instantiate an object of this class, to analyse, compare and change atributes of the object.</p>
 * <p>Instantiating a null CalendarDate will pass to the date's atributes, the moment of the command. Which is to say, that moment's minute, hour, day, month and year.</p>
 * @author Plácido Melo
 */
public class CalendarDate implements Comparable<CalendarDate>
{
    /**The CalendarDate's value for minute. Its range goes from 0 to 59.*/
    private int minute;
    /**The CalendarDate's value for hour. Its range goes from 0 to 23.*/
    private int hour;
    /**The CalendarDate's value for day. Its range goes from 1 to 28 (or 29), 30 or 31, acording to the month.*/
    private int day;
    /**The CalendarDate's value for month. Its range goes from 1 to 12.*/
    private int month;
    /**The CalendarDate's value for year.*/
    private int year;

    public CalendarDate()
    {
        Calendar gregCalendar = new GregorianCalendar();
        
        this.minute = gregCalendar.get(Calendar.MINUTE);
        this.hour = gregCalendar.get(Calendar.HOUR_OF_DAY);
        this.day = gregCalendar.get(Calendar.DAY_OF_MONTH);
        this.month = gregCalendar.get(Calendar.MONTH) + 1;
        this.year = gregCalendar.get(Calendar.YEAR);
    }

    public CalendarDate(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public CalendarDate(int minute, int hour, int day, int month, int year)
    {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    /**Returns a string value with this object's hour and minute in a "00h00" format.
     * @return a string value with the hour and minute information.
     */
    public String getTime()
    {
        return this.smallerThan10(this.hour) + "h" + this.smallerThan10(this.minute);
    }
    
    /**Returns the instance's day, month and year as a String that can be used as an ordering index.
     * @return the date as a string format yyyymmdd.
     */
    public String getOrderedDate()
    {
        return "" + this.year + this.smallerThan10(this.month) + this.smallerThan10(this.day);
    }
    
    @Override
    public int compareTo(CalendarDate d)
    {
        if (this.year == d.getYear() && this.month == d.getMonth() && this.day == d.getDay()) {
            return 0;
        } 
        return (this.beforeDate(d)?-1:1);
    }

    /**Returns the difference, in years, between this CalendarDate and the other CalendarDate passed on as a parameter.
     * @param otherCalendarDate the CalendarDate for which to be compared.
     * @return the quantity of years.
     */
    public int differenceYears(CalendarDate otherCalendarDate)
    {
        int difference = this.year - otherCalendarDate.getYear();
        
        return Math.abs(difference);
    }

    /**Returns the difference, in months, between this CalendarDate and the other CalendarDate passed on as a parameter.
     * @param otherCalendarDate the CalendarDate for which to be compared.
     * @return the quantity of months.
     */
    public int differenceMonths(CalendarDate otherCalendarDate)
    {
        int difference = (this.month - otherCalendarDate.getMonth()) + ((this.year - otherCalendarDate.getYear()) * 12);
        
        return Math.abs(difference);
    }
    
    /**<p>Returns the difference, in days, between this CalendarDate and the other CalendarDate passed on as a parameter.</p>
     * <p>This method is private and called by the method differenceDay(). It counts the number of days between months apart, taking into consideration leap years.</p>
     * <p>The CalendarDate passed on as a parameter must be the oldest.</p>
     * @param finalDate the CalendarDate that represents the last month of the count.
     * @return the quantity of days.
     */
    private int differenceMonthsInDays(CalendarDate finalDate)
    {
        int refMonth = this.month, refYear = this.year, totalDays = 0;
        
        if ((refMonth < finalDate.getMonth()) && (refYear == finalDate.getYear()))
        {
            do
            {                
                totalDays += daysMonth(refMonth, this.year);
                refMonth++;
            } while (refMonth < finalDate.getMonth());
        }
        else if ((refMonth != finalDate.getMonth()) && (refYear != finalDate.getYear()))
        {
            do
            {
                totalDays += daysMonth(refMonth, refYear);
                refMonth++;
                if (refMonth == 13)
                {
                    refMonth = 1;
                    refYear++;
                }
            } while ((refMonth != finalDate.getMonth()) || (refYear != finalDate.getYear()));
        }
        
        return totalDays;
    }

    /**<p>Returns the difference, in days, between this CalendarDate and the other CalendarDate passed on as a parameter.</p>
     * <p>The CalendarDate passed on as a parameter must be the oldest.</p>
     * @param finalDate the CalendarDate that represents the last day of the count.
     * @return the quantity of days.
     */
    public int differenceDay(CalendarDate finalDate)
    {
        int totalDays = 0;
        totalDays += this.differenceMonthsInDays(finalDate);
        
        if (this.day < finalDate.getDay())
        {
            totalDays += finalDate.getDay() - this.day;
        }
        else
        {
            totalDays += (this.day - finalDate.getDay()) * (-1);
        }
        
        return totalDays;
    }

    /**<p>Compares this to another CalendarDate, given as a parameter, and returns if this is a earlier date or not.</p>
     * <p>true - this CalendarDate is earlier than otherCalendarDate.</p>
     * <p>false - this CalendarDate is equal or later than otherCalendarDate.</p>
     * @param otherCalendarDate the CalendarDate to which compare this date.
     * @return a boolean value to verify if this CalendarDate is earlier.
     */
    public boolean beforeDate(CalendarDate otherCalendarDate)
    {
        if (this.year < otherCalendarDate.getYear())
        {
            return true;
        }
        else if (this.year == otherCalendarDate.getYear())
        {
            if (this.month < otherCalendarDate.getMonth())
            {
                return true;
            }
            else if (this.month == otherCalendarDate.getMonth())
            {
                if (this.day < otherCalendarDate.getDay())
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**Increments this CalendarDate in 1 minute. If this CalendarDate is in the last minute of the hour (59), this method will increment the hour.*/
    public void incrementarMinuto()
    {
        if (this.minute == 59)
        {
            this.incrementHour();
            this.minute = 0;
        }
        else
        {
            this.minute++;
        }
    }
    
    /**Increments this CalendarDate in 1 hour. If this CalendarDate is in the last hour of the day (23h), this method will increment the day.*/
    public void incrementHour()
    {
        if (this.hour == 23)
        {
            this.incrementDate();
            this.hour = 0;
        }
        else
        {
            this.hour++;
        }
    }
    
    /**Increments this CalendarDate in 1 day. If this CalendarDate is in the last day of the month, or of the year, this method will increment the or the year, accordingly.*/
    public void incrementDate()
    {
        if (this.day == daysMonth(this.month, this.year))
        {
            this.day = 1;
            
            if (this.month == 12)
            {
                this.month = 1;
                this.year++;
            }
            else
            {
                this.month++;
            }
        }
        else
        {
            this.day++;
        }
    }
    
    /**<p>Returns an integer value representing the maximum number of days the parametered month has in that parametered year.</p>
     * <p>Tests leap year to return the true value for the month of February.</p>
     * @param month the integer value representing the month, ranges from 1 to 12.
     * @param year the integer value of the year.
     * @return the maximum number of days in the month, as an integer value.
     */
    public static int daysMonth(int month, int year)
    {
        switch(month)
        {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year))
                {
                    return 29;
                }
                return 28;
            default:
                return 31;
        }
    }
    
    /**<p>Returns a boolean value that verifies if the year passed on as a parameter is a leap year or not.</p>
     * <p>true - the year is a leap year.</p>
     * <p>false - the year is not a leap year.</p>
     * @param year the year to be verified.
     * @return verification of the leap year, as a boolean value.
     */
    public static boolean isLeapYear(int year)
    {
        return ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)));
    }
    
    @Override
    public String toString()
    {
        return this.smallerThan10(this.day) + "/" + this.smallerThan10(this.month) + "/" + this.year;
    }
    
    /**<p>Returns a string that turns any integer of 1 digit into a two digit, being the first digit a 0.</p>
     * <p>This method is private and is called to return values such as 01, 02 or 05 for methods using strings, such as toString(), and print strings such as 02/04/2016.</p>
     * @param i the integer value.
     * @return a string of two digits.
     */
    private String smallerThan10(Integer i)
    {
        if (i < 10)
            return "0" + i;
        else
            return String.valueOf(i);
    }
    
    
    
    
    
    // Getters -----------------------------------------------------------------
    
    /**Getter for the CalendarDate's minute attribute. The numeric value of the minute.
     * @return the minute as an integer value.
     */
    public int getMinute()
    {
        return this.minute;
    }
    
    /**Getter for the CalendarDate's hour attribute. The numeric value of the hour.
     * @return the hour as an integer value.
     */
    public int hour()
    {
        return this.hour;
    }
    
    /**Getter for the CalendarDate's day. The numeric value of the day of the month.
     * @return the day
     */
    public int getDay()
    {
        return day;
    }
    
    /**Getter for the CalendarDate's month. The numeric value for the month of the year.
     * @return the month
     */
    public int getMonth()
    {
        return month;
    }
    
    /**Getter for the CalendarDate's year. The numeric value of the year.
     * @return the year
     */
    public int getYear()
    {
        return year;
    }
    
    // Setters -----------------------------------------------------------------

    /**Setter for the CalendarDate's minute. The parameter value must be an integer between 0 and 59.
     * @param minute the minute to set
     * @throws OutOfRangeValuesException thrown if the value is out of specified ranges
     */
    public void setMinute(int minute) throws OutOfRangeValuesException
    {
        if ((minute < 0) || (minute > 59))
        {
            throw new OutOfRangeValuesException("O valor introduzido encontra-se fora dos limites validáveis (0 - 59)");
        }
        this.minute = minute;
    }
    
    /**Setter for the CalendarDate's hour. The parameter value must be an integer between 0 and 23.
     * @param hour the hour to set
     * @throws OutOfRangeValuesException thrown if the value is out of specified ranges
     */
    public void setHour(int hour) throws OutOfRangeValuesException
    {
        if ((hour < 0) || (hour > 23))
        {
            throw new OutOfRangeValuesException("O valor introduzido encontra-se fora dos limites validáveis (0 - 23)");
        }
        this.hour = hour;
    }
    
    /**Setter for the CalendarDate's day. The parameter must be an integer between 1 and 28, 30 or 31, according to the month.
     * @param day the day to set
     * @throws OutOfRangeValuesException thrown if the value is out of specified ranges
     */
    public void setDay(int day) throws OutOfRangeValuesException
    {
        int maxVal = daysMonth(this.month, this.year);
        
        if ((day < 1) || (day > maxVal))
        {
            throw new OutOfRangeValuesException("O valor introduzido encontra-se fora dos limites validáveis (0 - " + maxVal + ")");
        }
        this.day = day;
    }

    /**Setter for the CalendarDate's month. The parameter must be an integer between 1 and 12. 
     * @param month the month to set
     * @throws OutOfRangeValuesException thrown if the value is out of specified ranges
     */
    public void setMonth(int month) throws OutOfRangeValuesException
    {
        if ((month < 1) || (month > 12))
        {
            throw new OutOfRangeValuesException("O valor introduzido encontra-se fora dos limites validáveis (1 - 12)");
        }
        this.month = month;
    }

    /**Setter for the CalendarDate's year. The parameter must be an integer.
     * @param year the year to set
     */
    public void setYear(int year)
    {
        this.year = year;
    }
}