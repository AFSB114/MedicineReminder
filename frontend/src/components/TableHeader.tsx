import React from 'react';
import { ArrowUpDown } from 'lucide-react';

interface TableHeaderProps {
  label: string;
  sortable?: boolean;
  onClick?: () => void;
}

const TableHeader: React.FC<TableHeaderProps> = ({ 
  label, 
  sortable = false,
  onClick
}) => {
  return (
    <th 
      className={`px-4 py-3 text-left text-xs font-medium text-gray-300 bg-zinc-950 uppercase tracking-wider ${sortable ? 'cursor-pointer hover:bg-zinc-900 transition-colors duration-200' : ''}`}
      onClick={sortable ? onClick : undefined}
    >
      <div className="flex items-center space-x-1">
        <span>{label}</span>
        {sortable && (
          <ArrowUpDown size={14} className="text-gray-400" />
        )}
      </div>
    </th>
  );
};

export default TableHeader;