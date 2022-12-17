/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processschedulersimulation;

import java.util.Comparator;

/**
 *
 * @author Hp
 */
public class ProcessComparatorPriority implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        return o1.getPriority() > o2.getPriority() ? 1 : -1;
    }

}
